package model;

public class FilterAll extends SeqEvaluator {

	public FilterAll(int maxSeqOps) {
		super(maxSeqOps);
	}
	
	public String toString() {
		
		int invalidOps = 0;
		
		for (int i = 0; i < this.noso; i ++) {
			
			if (!(this.seqOps[i] instanceof OccursWithin)) {
				invalidOps ++;
			}
			
		}
		
		if (invalidOps > 0) {
			return String.format("Filter cannot be evaluated due to %d incompatile operations.", invalidOps);
		}
		
		String[] filter = new String[this.noso];
		int count = 0;
		for (int i = 0; i < this.noso; i ++) {
			SeqOperation seqOp = this.seqOps[i];
			
			if (seqOp instanceof OccursWithin) {
				
				if (((OccursWithin) seqOp).getOccurrence()) {
					filter[count] = "true";
					count ++;
				} else {
					filter[count] = "_";
					count ++;
				}
				
			}
		}
		
		String list = "";
		
		for (int i = 0; i < this.noso; i ++) {
			list += filter[i];
			if (i != this.noso-1) {
				list += ", ";
			}
		}
		
		String result = String.format("Filter result is: %s", list);
		
		return result;
	}
}

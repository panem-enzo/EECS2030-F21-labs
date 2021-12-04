package model;

public class ConcatAll extends SeqEvaluator {

	public ConcatAll(int maxSeqOps) {
		super(maxSeqOps);
	}
	
	public String toString() {
		
		String result = null;
		
		
		
		return "Concat([1, 3, 1, 5, 3], [0, 1, 4, 9], []) = [1, 3, 1, 5, 3, 0, 1, 4, 9]";
	}
}
 
package model;

public class SumsOfPrefixes extends SeqOperation {

	public SumsOfPrefixes(int[] seq1) {
		this.seq1 = seq1;
	}
	
	public String toString() {
		
		int[] prefixSum = new int[seq1.length+1];
		prefixSum[0] = 0;
		
		if (seq1.length > 0) {
			
			int sum = 0;
			for (int i = 0; i < seq1.length; i ++) {
				sum += seq1[i];
				prefixSum[i+1] += sum;
			}
			
		}
		
		String result = String.format("Sums of prefixes of %s is: %s", this.seqBracket(this.seq1),this.seqBracket(prefixSum));
		
		return result;
	}
	
}

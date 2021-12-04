package model;

public class SumsOfPrefixes extends SeqOperation {

	private int[] prefixSum;
	
	public SumsOfPrefixes(int[] seq1) {
		this.seq1 = seq1;
		this.prefixSum = new int[seq1.length+1];
		
		prefixSum[0] = 0;
		
		if (seq1.length > 0) {
			
			int sum = 0;
			for (int i = 0; i < seq1.length; i ++) {
				sum += seq1[i];
				prefixSum[i+1] += sum;
			}
			
		}
	}
	
	public int[] getSequence() {
		return this.prefixSum;
	}
	
	public int getSequenceLength() {
		return this.prefixSum.length;
	}
	
	public String toString() {
		
		String result = String.format("Sums of prefixes of %s is: %s", this.seqBracket(seq1),this.seqBracket(prefixSum));
		
		return result;
	}
	
}

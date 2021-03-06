package model;

public class Projection extends BinarySeqOperation {

	private int[] proj;
	
	public Projection(int[] seq1, int[] seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
		
		int[] tempProj = new int[seq2.length];
		int count = 0;

		for (int i = 0; i < seq2.length; i ++) {

			boolean match = false;

			for (int j = 0; !match && j < seq1.length; j ++) {
				if (seq2[i] == seq1[j]) {
					tempProj[count]= seq2[i];
					count ++;
					match = true;
				}
			}
		}
		
		proj = new int[count];

		if (count == 0) {
			proj = new int[0];
		} else {
			
			for (int i = 0; i < count; i ++) {
				proj[i] = tempProj[i];
			}
			
		}
		
	}
	
	public int[] getSequence() {
		return this.proj;
	}
	
	public int getSequenceLength() {
		return this.proj.length;
	}
	
	public String toString() {

		String result = String.format("Projecting %s to %s results in: %s", seqBracket(seq1), seqBracket(seq2), seqBracket(proj));

		return result;
	}

}

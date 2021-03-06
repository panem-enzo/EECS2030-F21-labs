package model;

public class SeqOperation {
	
	protected int[] seq1;
	protected int[] seq2;
	
	public String seqBracket(int[] seq) {

		String seqBracket = "[";

		if (seq.length != 0) {

			for (int i = 0; i < seq.length; i ++) {
				seqBracket += seq[i];

				if (i != seq.length-1) {
					seqBracket += ", ";
				}
			}

		}

		seqBracket += "]";

		return seqBracket;
	}

}

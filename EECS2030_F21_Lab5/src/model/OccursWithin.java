package model;

public class OccursWithin extends BinarySeqOperation {

	private boolean occurs = false;

	public OccursWithin(int[] seq1, int[] seq2) {
		this.seq1 = seq1;
		this.seq2 = seq2;
		
		if (seq1.length != 0 && seq2.length != 0 && seq1.length <= seq2.length) {

			for (int i = 0; !occurs && i < seq2.length; i ++) {

				int j = 0;
				if (seq2[i] == seq1[j]) {
					
					occurs = true;
					int current = i;
					while (occurs) {

						if (seq2[current] == seq1[j]) {
							occurs = true;
						} else {
							occurs = false;
						}

						j ++;
						current ++;

						if (current > seq2.length-1 || j > seq1.length-1) {
							break;
						}

					}
				}

			}

		} else if (seq1.length == 0) {
			occurs = true;
		}

	}
	
	public boolean getOccurrence() {
		return this.occurs;
	}

	public String toString() {

		String result = null;

		if (occurs == true) {
			result = String.format("%s occurs within %s", seqBracket(seq1), seqBracket(seq2));
		} else {
			result = String.format("%s does not occur within %s", seqBracket(seq1), seqBracket(seq2));
		}

		return result;

	}

}

package model;

public class ConcatAll extends SeqEvaluator {

	private int[] concatAllSequence;

	public ConcatAll(int maxSeqOps) {
		super(maxSeqOps);
		
	}

	private int[] getSeq(SeqOperation seqOp) {

		int[] seq = null;

		if (seqOp instanceof Projection) {
			seq = ((Projection) seqOp).getSequence();
		} else if (seqOp instanceof SumsOfPrefixes) {
			seq = ((SumsOfPrefixes) seqOp).getSequence();
		}

		return seq;
	}

	private int getSeqOpLength(SeqOperation seqOp) {

		int seqOpLength = 0;
		if (seqOp instanceof Projection) {
			seqOpLength = ((Projection) seqOp).getSequenceLength();
		} else if (seqOp instanceof SumsOfPrefixes) {
			seqOpLength = ((SumsOfPrefixes) seqOp).getSequenceLength();
		}

		return seqOpLength;
	}

	public String toString() {
		
		int invalidOps = 0;
		for (int i = 0; i < this.noso; i ++) {
			if (!(this.seqOps[i] instanceof Projection || this.seqOps[i] instanceof SumsOfPrefixes)) {
				invalidOps ++;
			}
		}
		
		if (invalidOps > 0) {
			return String.format("Concat cannot be evaluated due to %d incompatile operations.", invalidOps);
		}
		
		int numElements = 0;
		for (int i = 0; i < this.noso; i ++) {
			numElements += getSeqOpLength(this.seqOps[i]);
		}

		concatAllSequence = new int[numElements]; 

		int k = 0;
		for (int i = 0; i < this.noso; i ++) {
			SeqOperation seqOp = this.seqOps[i];
			int seqOpLength = getSeqOpLength(this.seqOps[i]);

			for (int j = 0; j < seqOpLength; j ++) {
				concatAllSequence[k] = getSeq(seqOp)[j];
				k ++;
			}
		}

		// Curved brackets for seq ops
		String list = "(";

		for (int i = 0; i < this.noso; i ++) {

			SeqOperation seqOp = this.seqOps[i];
			list += seqOp.seqBracket(getSeq(seqOp));

			if (i != this.noso-1) {
				list += ", ";
			}
		}

		list += ")";

		// Creating brackets for concatAllSequence
		String seqBracket = "[";

		if (concatAllSequence.length != 0) {

			for (int i = 0; i < concatAllSequence.length; i ++) {
				seqBracket += concatAllSequence[i];

				if (i != concatAllSequence.length-1) {
					seqBracket += ", ";
				}
			}

		}

		seqBracket += "]";

		String result = String.format("Concat%s = %s", list, seqBracket);

		return result;
	}
}

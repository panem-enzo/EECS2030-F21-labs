package model;

public abstract class SeqEvaluator {

	protected SeqOperation[] seqOps; 
	protected int noso;
	
	public SeqEvaluator(int maxSeqOps) {
		this.seqOps = new SeqOperation[maxSeqOps];
	}
	
	public void addOperation(String operation, int[] seq1, int[] seq2) throws IllegalOperationException {
		
		if (operation.equals("op:projection")) {
			this.seqOps[this.noso] = new Projection(seq1,seq2);
			this.noso ++;
		} else if (operation.equals("op:sumsOfPrefixes")) {
			this.seqOps[this.noso] = new SumsOfPrefixes(seq1);
			this.noso ++;
		} 
		
	}
	
}

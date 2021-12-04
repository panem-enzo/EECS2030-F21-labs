package model;

public abstract class SeqEvaluator {

	SeqOperation[] seqOps; 
	int noso;
	
	public SeqEvaluator(int maxSeqOps) {
		this.seqOps = new SeqOperation[maxSeqOps];
	}
	
	public void addOperation(String operation, int[] seq1, int[] seq2) throws IllegalOperationException {
		
		SeqOperation seqOp = null;
		if (operation.equals("op:projection")) {
			seqOp = new Projection(seq1,seq2);
		} else if (operation.equals("op:sumsOfPrefixes")) {
			seqOp = new SumsOfPrefixes(seq1);
		} 
		
		
		
	}
	
}

package model;

public class Follower {
	
	protected String name;
	protected String type;
	protected Channel[] channels;
	protected int noc;
	
	public Follower() {}
	
	protected String chaList() {
		
		String chaList = "[";
		
		for (int i = 0; i < this.noc; i ++) {
			
			chaList += channels[i].name;
			
			if (i != this.noc - 1) {
				chaList += ", ";
			}
			
		}
		
		chaList += "]";
		
		return chaList;
		
	}
	
}
package model;

public class Monitor extends Follower {

	private String status;
	protected String stats;
	protected int views;
	protected int totalWatchTime;
	protected int maxWatchTime;
	protected double avgWatchTime;
	
	public Monitor(String name, int maxChannels) {
		super.type = "Monitor";
		super.name = name;
		super.channels = new Channel[maxChannels];
	}
	
	protected String chaList() {
		
		String chaList = "[";
		
		for (int i = 0; i < this.noc; i ++) {
			
			chaList += channels[i].name;
			
			if (this.views > 0) {
				chaList += this.stats;
			}
	
			if (i != this.noc - 1) {
				chaList += ", ";
			}
			
		}
		
		chaList += "]";
		
		return chaList;
		
	}
	
	@Override
	public String toString() {
		
		if (super.noc != 0) {
			this.status = String.format("%s %s follows %s.", super.type, super.name, this.chaList());
		} else {
			this.status = String.format("%s %s follows no channels.", super.type, super.name);
		}
		
		return this.status;
	}
	
}
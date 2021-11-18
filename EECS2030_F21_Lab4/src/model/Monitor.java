package model;

public class Monitor extends Follower {

	private String status;
	protected int[] views;
	protected int[] totalWatchTime;
	protected int[] maxWatchTime;
	protected double[] avgWatchTime;
	
	public Monitor(String name, int maxChannels) {
		
		super.type = "Monitor";
		super.name = name;
		super.channels = new Channel[maxChannels];
	
	}
	
	protected String chaList() {
		
		String chaList = "[";
		
		for (int i = 0; i < super.noc; i ++) {
			
			chaList += channels[i].name;
			
			boolean foundMonitor = false;
			for (int j = 0; !foundMonitor && j < channels[i].nof; j ++) {
				String monitorInChannel = channels[i].followers[j].name;
				
				if (monitorInChannel.equals(this.name)) {
					foundMonitor = true;
				}
			
			}
			
			if (foundMonitor && this.views != null) {
				
				if (this.views[i] != 0) {
					chaList += String.format(" {#views: %d, max watch time: %d, avg watch time: %.2f}", this.views[i], this.maxWatchTime[i], this.avgWatchTime[i]);
				}
				
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
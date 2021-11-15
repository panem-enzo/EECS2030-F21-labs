package model;

public class Monitor extends Follower {

	private String status;
	
	public Monitor(String name, int maxChannels) {
		super.name = String.format("Monitor %s", name);
		super.channels = new Channel[maxChannels];
		this.status = String.format("%s follows no channels.", super.name);
	}
	
	@Override
	public String toString() {
		
		if (super.noc != 0) {
			this.status = String.format("%s follows %s", super.name, )
		}
		
		return this.status;
	}
	
}

package model;

public class Monitor extends Follower {

	private String status;
	
	public Monitor(String name, int maxChannels) {
		super.type = "Monitor";
		super.name = name;
		super.channels = new Channel[maxChannels];
	}
	
	@Override
	public String toString() {
		
		if (super.noc != 0) {
			this.status = String.format("%s %s follows %s.", super.type, super.name, super.chaList());
		} else {
			this.status = String.format("%s %s follows no channels.", super.type, super.name);
		}
		
		return this.status;
	}
	
}

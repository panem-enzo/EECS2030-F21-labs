package model;

public class Subscriber extends Follower {

	private String status;
	private String[] vidRec;
	
	public Subscriber(String name, int maxChannels, int maxVideoRec) {
		super.name = String.format("Subscriber %s", name);
		super.channels = new Channel[maxChannels];
		this.vidRec = new String[maxVideoRec];
	}

//	private vidRecList() {
//		
//	}
	 
	@Override
	public String toString() {
		
		if (super.noc != 0) {
			this.status = String.format("%s follows %s and has no recommended videos.", super.name, super.chaList());
		} else {
			this.status = String.format("%s follows no channels and has no recommended videos.", super.name);
		}
		
		return this.status;
	}
	
}

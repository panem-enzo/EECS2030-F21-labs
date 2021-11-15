package model;

public class Subscriber extends Follower {

	private String status;
	protected String[] vidRec;
	protected int novr;
	
	public Subscriber(String name, int maxChannels, int maxVideoRec) {
		super.type = "Subscriber";
		super.name = name;
		super.channels = new Channel[maxChannels];
		this.vidRec = new String[maxVideoRec];
	}

	private String vidRecList() {
		
		String vidRecList = "is recommended <";
		
		for (int i = 0; i < this.novr; i ++) {
			
			vidRecList += vidRec[i];
			
			if (i != this.novr - 1) {
				vidRecList += ", ";
			}
			
		}
		
		vidRecList += ">";
		
		return vidRecList;
		
	}
	 
	@Override
	public String toString() {
		
		if (super.noc != 0 && this.novr != 0) {
			this.status = String.format("%s %s follows %s and %s.", super.type, super.name, super.chaList(), vidRecList());
		} else if (super.noc != 0 && this.novr == 0) {
			this.status = String.format("%s %s follows %s and has no recommended videos.", super.type, super.name, super.chaList());
		} else {
			this.status = String.format("%s %s follows no channels and has no recommended videos.", super.type, super.name);
		}
		
		return this.status;
	}
	
}

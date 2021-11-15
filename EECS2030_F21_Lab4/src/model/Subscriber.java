package model;

public class Subscriber extends Follower {

	private String status;
	private int maxVideoRec;
	
	public Subscriber(String name, int maxChannels, int maxVideoRec) {
		super.name = String.format("Subscriber %s", name);
		super.channels = new Channel[maxChannels];
		this.maxVideoRec = maxVideoRec;
		this.status = String.format("%s follows no channels and has no recommended videos.", super.name);
	}

//	if (super.nof != 0) {
//		
//		String list = "[";
//		
//		for (int i = 0; i < super.nof; i ++) {
//			
//			list += super.channels[i].name;
//			
//			if (i != super.nof - 1) {
//				list += ", ";
//			}
//		}
//		
//		list += "]";
//		
//		this.status = String.format("%s follows %s and has no recommended videos.", super.name, list);
//	}
	
	@Override
	public String toString() {
		
		
		
		return this.status;
	}
	
}

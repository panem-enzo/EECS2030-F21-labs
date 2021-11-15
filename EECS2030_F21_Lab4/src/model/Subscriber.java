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

	public void watch(String video, int time) {
		
		// Find the channel with this video (assuming all videos are unique)
		
		boolean videoFound = false;
		Channel channel = null;
		
		for (int i = 0; i < super.noc; i ++) {
			for (int j = 0; !videoFound && j < super.channels[i].nov; j ++) {
				
				if (super.channels[i].videos[j].equals(video)) {
					videoFound = true;
					channel = super.channels[i];
				}
				
			}
		}
		
		int count = 0;
		
		for (int i = 0; i < channel.nof; i ++) {
			
			if (channel.followers[i].type.equals("Monitor")) {
				
				channel.views ++;
				channel.totalWatchTime += time;
				channel.avgWatchTime = (double) channel.totalWatchTime / channel.views;

				if (time > channel.maxWatchTime) {
					channel.maxWatchTime = time;
				}
				
				channel.stats[count] = String.format(" {#views: %d, max watch time: %d, avg watch time: %.2f}", channel.views, channel.maxWatchTime, channel.avgWatchTime);
				count ++;
				
			}
		}
		
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

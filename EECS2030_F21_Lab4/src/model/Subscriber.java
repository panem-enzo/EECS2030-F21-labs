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
		
		// Find the subscribed channel with this video (assuming all videos are unique)
		
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
		
		//Iterating through the followers of a PARTICULAR channel
		for (int i = 0; i < channel.nof; i ++) {
			
			if (channel.followers[i].type.equals("Monitor")) {
				
				Monitor monitor = (Monitor) channel.followers[i];
				
				channel.views[channel.monitorIndex] ++;
				channel.totalWatchTime[channel.monitorIndex] += time;
				channel.avgWatchTime[channel.monitorIndex] = (double) channel.totalWatchTime[channel.monitorIndex] / channel.views[channel.monitorIndex];
				
				if (time > channel.maxWatchTime[channel.monitorIndex]) {
					channel.maxWatchTime[channel.monitorIndex] = time;
				}
				
				if (monitor.views == null) {
					
					monitor.views = new int[monitor.noc];
					monitor.totalWatchTime = new int[monitor.noc];
					monitor.maxWatchTime = new int[monitor.noc];
					monitor.avgWatchTime = new double[monitor.noc];
					
				}
				
				int channelIndex = 0;
				
				for (int j = 0; j < monitor.noc; j ++) {
					
					if (monitor.channels[j].name.equals(channel.name)) {
						channelIndex = j;
					}
	
				}
				
				monitor.views[channelIndex] ++;
				monitor.totalWatchTime[channelIndex] += time;
				monitor.avgWatchTime[channelIndex] = (double) monitor.totalWatchTime[channelIndex] / monitor.views[channelIndex];
				
				if (time > 	monitor.maxWatchTime[channelIndex]) {
					monitor.maxWatchTime[channelIndex] = time;
				}
				
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
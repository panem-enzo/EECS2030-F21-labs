package model;

public class Channel {

	private String status;
	protected Follower[] followers;
	protected int nof;
	protected String name;
	protected int maxFollowers;
	protected String[] videos;
	protected int nov;
	protected int monitorIndex; //number of monitors tracked (stats)
	protected int views[];
	protected int totalWatchTime[];
	protected int maxWatchTime[];
	protected double avgWatchTime[];

	public Channel(String name, int maxFollowers, int maxVideos) {
		this.name = name;
		this.maxFollowers = maxFollowers;
		this.videos = new String[maxVideos];
		this.followers = new Follower[maxFollowers];
		
		this.views = new int[maxFollowers];
		this.totalWatchTime = new int[maxFollowers];
		this.maxWatchTime = new int[maxFollowers];
		this.avgWatchTime = new double[maxFollowers];
	}

	public void releaseANewVideo(String video) {
		videos[this.nov] = video;
		this.nov++;
		
		for (int i = 0; i < this.nof; i ++) {
			if ((followers[i]).type.equals("Subscriber")) {
				
				 Subscriber sub = (Subscriber) followers[i];
				 sub.vidRec[sub.novr] = video;
				 sub.novr ++;
				 
			}
		}
		
	}

	public void follow(Follower follower) {

		follower.channels[follower.noc] = this;
		follower.noc++;

		followers[this.nof] = follower;
		this.nof++;
		
		// Finds an existing monitor and will only shift the index for storing the stats if the monitor is unique
		
		boolean valid = false;
		
		for (int i = 0; !valid && i < nof; i ++) {
			
			if (this.followers[i].type.equals("Monitor") && follower.type.equals("Monitor") && (!(this.followers[i].name.equals(follower.name)))) {
				
				Monitor monitor = (Monitor) follower;
				this.monitorIndex ++;
				valid = true;
				
			}
			
		}
		
		
	}

	public void unfollow(Follower follower) {
		
		Follower[] folTrim = new Follower[this.nof];
		int folCount = 0;
		boolean folFound = false;
		
		for (int i = 0; i < this.nof; i ++) {
			if (this.followers[i].name != follower.name) {
				folTrim[folCount] = this.followers[i];
				folCount ++;
			} else {
				folFound = true;
			}
		}
		
		if (folFound) {
			this.nof --;
			this.followers = folTrim;
		}
		
		Channel[] chaTrim = new Channel[follower.noc];
		int chaCount = 0;
		boolean chaFound = false;
		
		for (int i = 0; i < follower.noc; i ++) {
			if (follower.channels[i].name != this.name) {
				chaTrim[chaCount] = follower.channels[i];
				chaCount ++;
			} else {
				chaFound = true;
			}
		}
		
		if (chaFound) {
			follower.noc --;
			follower.channels = chaTrim;
		}
		
	}

	// Helper Methods

	private String vidList() {

		String vidList = "released <";

		for (int i = 0; i < this.nov; i++) {

			vidList += videos[i];
			if (i != this.nov - 1) {
				vidList += ", ";
			}

		}

		vidList += ">";
		return vidList;

	}

	private String folList() {

		String folList = "is followed by [";

		for (int i = 0; i < this.nof; i++) {

			folList += followers[i].type + " " + followers[i].name;

			if (i != this.nof - 1) {
				folList += ", ";
			}

		}

		folList += "]";
		return folList;

	}

	@Override
	public String toString() {

		if (this.nov != 0 && this.nof == 0) {
			this.status = String.format("%s %s and has no followers.", this.name, vidList());
		} else if (this.nov != 0 && this.nof != 0) {
			this.status = String.format("%s %s and %s.", this.name, vidList(), folList());
		} else if (this.nov == 0 && this.nof != 0) {
			this.status = String.format("%s released no videos and %s.", this.name, folList());
		} else {
			this.status = String.format("%s released no videos and has no followers.", this.name);
		}

		return this.status;
	}

}
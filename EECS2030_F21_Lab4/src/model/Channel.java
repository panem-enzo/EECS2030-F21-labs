package model;

public class Channel {

	protected String name;
	private String status;
	private String[] videos;
	private Follower[] followers;
	private int nov;
	private int nof;
	private int maxFollowers;
	private int maxVideos;
	
	public Channel(String name, int maxFollowers, int maxVideos) {
		this.name = name;
		this.maxFollowers = maxFollowers;
		this.maxVideos = maxVideos;
		this.videos = new String[maxVideos];
		this.followers = new Follower[maxFollowers];
		this.status = String.format("%s released no videos and has no followers.", this.name);
	}

	public void releaseANewVideo(String video) {
		videos[this.nov] = video;
		this.nov ++;
	}
	
	public void follow(Follower follower) {
		
		follower.channels[follower.noc] = this;
		follower.noc ++;
		
		followers[this.nof] = follower;
		this.nof ++;
	}
	 
	//Helper Methods
	
	private String vidList() {
		
		String vidList = "released <";
		
		for (int i = 0; i < this.nov; i ++) {
			
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
		
		for (int i = 0; i < this.nof; i ++) {
			
			folList += followers[i].name;
			
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
		}
		
		return this.status;
	}
	
}

package model;

public class Channel {

	private String name;
	private int nof;
	private int maxFollowers;
	private int maxVideos;
	
	public Channel(String name, int maxFollowers, int maxVideos) {
		this.name = name;
		this.maxFollowers = maxFollowers;
		this.maxVideos = maxVideos;
	}
	
}

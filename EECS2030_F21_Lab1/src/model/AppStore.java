package model;

public class AppStore {
	
	private String branch;
	private App[] apps;
	private int noa; /* number of apps */
	
	public AppStore(String branch, int maxAvailableApps) {
		this.branch = branch;
		this.apps = new App[maxAvailableApps];
		this.noa = 0;
	}
	
	public String getBranch() {
		return this.branch;
	}
	
	/* See getProduct(String s) in Review Series Part 2 
	 * See get getVersionInfo() */
	public App getApp(String nameOfApp) {
		
		App result = null;
		boolean foundMatch = false;
		
		for (int i = 0; i < this.noa && !foundMatch; i++) {
			App a = this.apps[i];
			if (this.apps[i].getName().equals(nameOfApp)) {
				result = a;
				foundMatch = true;
			}
		}
		
		return result;
	}
	
	/*
	 * See: getPointsInQuadrantI in written notes.
	 * See: getSpaceGreyOrPro in Review Series Part 2.
	 */
	
	public String[] getStableApps(int numberOfUpdates) {
		String[] stableApps = new String[this.noa];
		int count = 0;
		
		for (int i = 0; i < this.noa; i ++) {
			App app = this.apps[i];
			if (app.getUpdateHistory().length >= numberOfUpdates) {
				stableApps[count] = String.format("%s (%d versions; Current Version: %s)", app.getName(), app.getUpdateHistory().length, app.getWhatIsNew());
				count++;
			}
		}
		
		String[] stableAppsPrecise = new String[count];
		
		for (int i = 0; i < count; i ++) {
			stableAppsPrecise[i] = stableApps[i];
		}
		
		return stableAppsPrecise;

	}
	
	public void addApp(App app) {
		this.apps[this.noa] = app;
		this.noa++;
	}
	
}
package model;

public class AppStore {

	int maxNumApps;
	String branch;
	String[] stableApps = new String[maxNumApps];
	
	public AppStore(String branch, int maxNumApps) {
		
		this.branch = branch;
		this.maxNumApps = maxNumApps;
		
	}
	
	public String getBranch() {
		return this.branch;
	}
	
	public App getApp(String name) {
		
		App app = null;
		
		return app; 
	}
	
	public String[] getStableApps(int number) {
		
		String[] emptyStableApps = new String[0];
		String[] trimStableApps = new String[maxNumApps];

		// "Trimming" the updateHistory array to remove null lengths
		if (stableApps.length > 0) {

			for (int i = 0; i < stableApps.length; i++) {
				trimStableApps[i] = stableApps[i];
			}

			stableApps = trimStableApps;

		} else {
			stableApps = emptyStableApps;
		}

		return this.stableApps;
		
	}
	
	public void addApp(App app) {
		
	}
	
	
	
}


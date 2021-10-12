package model;

public class AppStore {

	String branch;
	App[] apps;
	int numApps;

	public AppStore(String branch, int maxApps) {
		this.branch = branch;
		this.apps = new App[maxApps];
	}

	public String getBranch() {
		return this.branch;
	}

	public App getApp(String appName) {

		App app = null;

		if (numApps != 0) {

			for (int i = 0; i < numApps; i++) {
				if (apps[i].getName().equals(appName)) {
					app = apps[i];
				}
			}

		}

		return app;

	}
	
	public String[] getStableApps(int constraint) {
		
		App[] newApps = new App[numApps];
		int count = 0;
		
		for (int i=0; i < numApps; i++) {
			
			if (apps[i].getUpdateHistory().length >= constraint) {
				newApps[count] = apps[i];
				count++;
			}
			
		}
		
		App[] stableApps = new App[count];
		for (int i=0; i < count; i++) {
			stableApps[i] = newApps[i];
		}
		
		String[] stableAppsStr = new String[count];
		
		// "GoodNotes 5 (3 versions; Current Version: Version 5.7.31 contains 1 fixes [Better logging])"
		for (int i = 0; i < count; i++) {
			
			int last = stableApps[i].getUpdateHistory().length-1;
			
			stableAppsStr[i] = stableApps[i].getName() + " (" + stableApps[i].getUpdateHistory().length + " versions; Current Version: Version "
					+ stableApps[i].updates[last].getVersion() + " contains " + stableApps[i].getUpdateHistory()[last].getNumberOfFixes() + 
					" fixes " + stableApps[i].getUpdateHistory()[last].getFixes() + ")";
		}
		
		return stableAppsStr;
			
	}

	public void addApp(App app) {
		apps[numApps] = app;
		numApps++;
	}

}

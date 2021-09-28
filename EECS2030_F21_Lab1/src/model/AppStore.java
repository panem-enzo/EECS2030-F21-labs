package model;

public class AppStore {

	int numApps = 0;
	int numStableApps = 0;
	int numUpdates = 0;
	String branch;
	String[] stableApps = new String[numApps];
	App[] apps;

	public AppStore(String branch, int maxNumApps) {

		this.branch = branch;
		this.apps = new App[maxNumApps];

	}

	public String getBranch() {
		return this.branch;
	}

	public App getApp(String name) {

		App app = null;

		// Borrowed "search" implementation from getVersionInfo() in App class

		if (numApps != 0) {
			
			for (int i = 0; i < numApps; i++) {

				if (apps[i].getName().equals(name)) {
					app = apps[i];
				}

			}
			
		}

		return app;
	}

	public String[] getStableApps(int numUpdates) {

		String[] emptyStableApps = new String[0];
		App[] newStableApps = new App[numApps];

		// Borrowed "trimming" method from getUpdateHistory() in App class

		if (numApps == 0) {

			stableApps = emptyStableApps;

		} else if (this.numUpdates != numUpdates) {

			//Determining the amount of stable apps based on the number (update versions).
			this.numUpdates = 0;
			
			for (int i = 0; i < numApps; i++) {
				
				if (apps[i].getUpdateHistory().length >= numUpdates) {
					newStableApps[numStableApps] = apps[i];
					numStableApps++;
					this.numUpdates++;
				}
				
			}

			stableApps = new String[numStableApps];
			int count = 0;
			
			for (int i = 0; i < numStableApps; i++) {
				
				if (newStableApps[i] == null) {
					continue;
				}
				
				stableApps[count] = newStableApps[i].getName() + " (" + newStableApps[i].numUpdates + " versions; Current Version: Version " + 
				newStableApps[i].getUpdateHistory()[newStableApps[i].numUpdates-1].getVersion() + " contains " + newStableApps[i].getUpdateHistory()[newStableApps[i].numUpdates-1].getNumberOfFixes() 
				+ " fixes " + newStableApps[i].getUpdateHistory()[newStableApps[i].numUpdates-1].getFixes() + ")";
				
				count++;
			}

		}

		return this.stableApps;

	}

	public void addApp(App app) {

		apps[numApps] = app;
		numApps++;

	}

}

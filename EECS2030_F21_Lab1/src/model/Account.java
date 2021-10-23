package model;

public class Account {

	private String status;
	private String name;
	private AppStore store;

	private String[] namesOfDownloadedApps;
	private int noa; /* number of downlaods*/

	public Account(String name, AppStore appStore) {
		this.name = name;
		this.store = appStore;
		this.namesOfDownloadedApps = new String[50];
		this.noa = 0;

		this.status = String.format("An account linked to the %s store is created for %s.", appStore.getBranch(), name);
	}

	public void download(String nameOfApp) {
		
		boolean nameExists = false;
		for (int i = 0; i < this.noa && !nameExists; i++) {
			if(this.namesOfDownloadedApps[i].equals(nameOfApp)) {
				nameExists = true;
			}
		}
		
		if (nameExists) {
			this.status = String.format("Error: %s has already been downloaded for %s.", nameOfApp, this.name);
		} else {
			this.namesOfDownloadedApps[this.noa] = nameOfApp;
			this.noa++;
			
			this.status = String.format("%s is successfully downloaded for %s.", nameOfApp, this.name);
		}
		
	}

	public void submitRating(String nameOfApp, int score) {

		boolean nameExists = false;
		for (int i = 0; i < this.noa && !nameExists; i++) {
			if(this.namesOfDownloadedApps[i].equals(nameOfApp)) {
				nameExists = true;
			}
		}
		
		if (nameExists) {
			
			this.getApp(nameOfApp).submitRating(score);
			
			this.status = String.format("Rating score %d of %s is successfully submitted for %s.", score, this.name, nameOfApp);
		} else {
			this.status = String.format("Error: %s is not a downloaded app for %s.", nameOfApp, this.name);
		}

	}

	public void switchStore(AppStore store) {
		this.store = store;
		this.status = String.format("Account for %s is now linked to the %s store.", this.name, store.getBranch());

	}

	public void uninstall(String nameOfApp) {
		// check to see if `nameOfApp` is the name of a downloaded app.
		// if non-existing, do nothing.
		// if existing, remove the app.

		// Initially, when an account is first created, no apps have been downloaded,
		// so therefore nothing can be uninstalled.
		boolean nameExists = false;
		for (int i = 0; i < this.noa && !nameExists; i++) {
			if(this.namesOfDownloadedApps[i].equals(nameOfApp)) {
				nameExists = true;
			}
		}
		
		if (nameExists) {
			//Exercise remove the nameOfApp from the namesOfDownloadedApps array
			// See the visual hints.
			
			int index = 0;
			boolean found = false;
			
			for (int i = 0; i < this.noa && found == false; i++) {
				if (this.namesOfDownloadedApps[i].equals(nameOfApp)) {
					this.namesOfDownloadedApps[i] = null;
					index = i;
					found = true;
				}
			}
			
			for (int i = index; i < this.noa-1; i ++) {
				this.namesOfDownloadedApps[i] = this.namesOfDownloadedApps[i+1];
			}
			
			this.noa--;
			this.status = String.format("%s is successfully uninstalled for %s.", nameOfApp, this.name);
			
		} else {
			this.status = String.format("Error: %s has not been downloaded for %s.", nameOfApp, this.name);
		}

	}

	public String[] getNamesOfDownloadedApps() {

		String[] names = new String[this.noa];

		for (int i = 0; i < this.noa; i++) {
			names[i] = this.namesOfDownloadedApps[i];
		}

		return names;
	}

	public App[] getObjectsOfDownloadedApps() {
		
		App[] apps = new App[this.noa];
		
		for (int i = 0; i < this.noa; i ++) {
			String nameOfApp = this.namesOfDownloadedApps[i];
			App app = this.getApp(nameOfApp);
			apps[i] = app;
		}
		
		return apps;
	}

	// Given the name of app, find its corresponding app object in the linked store.
	private App getApp(String nameOfApp) {
		
		return this.store.getApp(nameOfApp);
	}

	public String toString() {
		return this.status;
	}
}
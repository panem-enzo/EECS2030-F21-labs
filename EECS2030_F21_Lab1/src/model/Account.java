package model;

public class Account {

	int numDownload = 0;
	final int MAX_DOWNLOADS = 50;
	String accountName;
	AppStore appStore;
	String status;
	App[] downloadedApps = new App[MAX_DOWNLOADS];

	public Account(String accountName, AppStore appStore) {
		this.accountName = accountName;
		this.appStore = appStore;
		this.status = "An account linked to the " + appStore.branch + " store is created for " + accountName + ".";
	}

	public String[] getNamesOfDownloadedApps() {

		String[] nameOfDownloadedApps = new String[numDownload];

		for (int i = 0; i < numDownload; i++) {
			nameOfDownloadedApps[i] = downloadedApps[i].getName();
		}

		return nameOfDownloadedApps;
	}

	public App[] getObjectsOfDownloadedApps() {

		App[] noDownloads = new App[0];
		App[] trimmedArray = new App[numDownload];

		if (numDownload == 0) {
			downloadedApps = noDownloads;
		} else {

			int count = 0;
			for (int i = 0; i < numDownload; i++) {
				if (downloadedApps[i] != null) {
					trimmedArray[count] = downloadedApps[i];
					count++;
				}
			}
		}

		return trimmedArray;
	}

	public void uninstall(String name) {

		App[] newArray = new App[numDownload];
		boolean downloaded = false;
		int count = 0;

		// Checks if the app is downloaded
		for (int i = 0; i < numDownload; i++) {
			if ((downloadedApps[i].getName().equals(name))) {
				downloaded = true;
			} else {
				newArray[count] = downloadedApps[i];
				count++;
			}
		}

		if (downloaded == false) {
			this.status = "Error: " + name + " has not been downloaded for " + accountName + ".";
		} else {
			this.status = name + " is successfully uninstalled for " + accountName + ".";
			numDownload--;
		}

		this.downloadedApps = newArray;

	}

	public void submitRating(String name, int rating) {

		App[] newArray = new App[numDownload];

		boolean downloaded = false;
		int count = 0;

		for (int i = 0; i < numDownload; i++) {
			if ((downloadedApps[i].getName().equals(name))) {
				downloaded = true;
			} else {
				newArray[count] = downloadedApps[i];
				count++;
			}
		}

		if (downloaded == false) {
			this.status = "Error: " + name + " is not a downloaded app for " + accountName + ".";
		} else {

			for (int i = 0; i < numDownload; i++) {
				if (downloadedApps[i].getName().equals(name)) {
					downloadedApps[i].submitRating(rating);
				}
			}
			
			this.status = "Rating score " + rating + " of " + accountName + " is successfully submitted for " + name + ".";

		}

		this.downloadedApps = newArray;

	}

	public void switchStore(AppStore appStore) {

		this.appStore = appStore;
		this.status = "Account for " + accountName + " is now linked to the " + this.appStore.getBranch() + " store.";

	}

	public void download(String name) {

		boolean downloaded = false;

		// Checks if the app is downloaded
		for (int i = 0; i < numDownload; i++) {
			if ((downloadedApps[i].getName().equals(name))) {
				downloaded = true;
			}
		}

		if (downloaded == false) {

			downloadedApps[numDownload] = appStore.getApp(name);
			numDownload++;
			this.status = name + " is successfully downloaded for " + accountName + ".";

		} else {
			this.status = "Error: " + name + " has already been downloaded for " + accountName + ".";
		}

	}

	@Override
	public String toString() {

		return status;
	}

}

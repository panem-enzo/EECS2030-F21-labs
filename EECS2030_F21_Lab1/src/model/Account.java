package model;

public class Account {

	String accountName;
	AppStore appstore;
	App[] apps = new App[50];
	int numApps;
	String status;
	
	public Account(String accountName, AppStore appstore) {
		this.accountName = accountName;
		this.appstore = appstore;
	}
	
	public String[] getNamesOfDownloadedApps() {
		
		String[] namesOfDownloadedApps = new String[numApps];
		
		for (int i = 0; i < numApps; i++) {
			namesOfDownloadedApps[i] = apps[i].getName();
		}
		
		return namesOfDownloadedApps;
	}
	
	public App[] getObjectsOfDownloadedApps() {
		
		App[] newApps = new App[numApps];
		
		for (int i=0; i < numApps; i++) {
			newApps[i] = apps[i];
		}
		
		return newApps;
	}
	
	public void uninstall(String appName) {
		
		App[] trimApps = new App[numApps];
		int count = 0;
		
		if (numApps != 0) {
			
			for (int i=0; i < apps.length; i++) {
				if (!(apps[i].getName().equals(appName))) {
					trimApps[count] = apps[i];
					count++;
				}
			}
			
			this.apps = trimApps;
			
		}
		
		
	}
	
	public void submitRating(String appName, int rating) {
		
	}
	
	public void switchStore(AppStore appstore) {
		this.appstore = appstore;
	}
	
	public void download(String appName) {
		apps[numApps] = this.appstore.getApp(appName);
		numApps++;
	}

	@Override
	public String toString() {
		return "An account linked to the " + appstore.getBranch() + " store is created for " + this.accountName + ".";
	}
	
	
}

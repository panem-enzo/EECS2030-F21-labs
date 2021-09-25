package model;

public class App {

	int maxRatings;
	final int MAX_UPDATE_LOGS = 20;
	String name;
	String ratingReport = "No ratings submitted so far!";
	String update = "n/a";
	String currentVersion = "n/a";
	String avgRating = "n/a";
	String[] ratings = new String[maxRatings];
	Log[] updateHistory = new Log[0];
	
	public App(String name, int maxRatings) {
		this.name = name;
		this.maxRatings = maxRatings;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getWhatIsNew() {
		return this.update;
	}
	
	public Log[] getUpdateHistory() {
		return this.updateHistory;
	}
	
	public Log getVersionInfo(String version) {
		
		Log log = null;
		
		// Search through updateHistory for log with version number
		for (int i = 0; i < updateHistory.length; i++) {
			
			if (log.getVersion().equals(version)) {
				log = updateHistory[i];
			}
			
		}
		
		return log;
	}
	
	public String getRatingReport() {
		return this.ratingReport;
	}

	@Override
	public String toString() {
		return name + " (Current Version: " + currentVersion + "; Average Rating: " + avgRating+  ")";
	}
	
}

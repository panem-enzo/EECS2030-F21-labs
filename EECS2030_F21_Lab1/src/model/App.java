package model;

public class App {

	int maxRatings;
	int numUpdates = 0;
	int[] ratings = new int[maxRatings]; 
	final int MAX_UPDATE_LOGS = 20;
	String name;
	String ratingReport = "No ratings submitted so far!";
	String update = "n/a";
	String avgRating = "n/a";
	Log[] updateHistory = new Log[MAX_UPDATE_LOGS];

	public App(String name, int maxRatings) {
		this.name = name;
		this.maxRatings = maxRatings;
		
	}

	public String getName() {
		return this.name;
	}

	public String getWhatIsNew() {

		String currentVersion = "n/a";
		
		if (numUpdates > 0) {
			currentVersion = updateHistory[numUpdates-1].toString();
		}

		return currentVersion;
	}

	public Log[] getUpdateHistory() {

		Log[] emptyUpdateHistory = new Log[0];
		Log[] trimUpdateHistory = new Log[numUpdates];

		// "Trimming" the updateHistory array to remove null lengths
		if (numUpdates > 0) {
			
			for (int i = 0; i < numUpdates; i++) {
				trimUpdateHistory[i] = updateHistory[i];
			}

			updateHistory = trimUpdateHistory;
			
		}
		else {
			updateHistory = emptyUpdateHistory;
		}
		
		return this.updateHistory;
	}

	public Log getVersionInfo(String version) {

		Log log = null;

		// Search through updateHistory for log with version number
		for (int i = 0; i < updateHistory.length; i++) {

			if (updateHistory[i].getVersion().equals(version)) {
				log = updateHistory[i];
			}

		}

		return log;
	}

	public String getRatingReport() {
		return this.ratingReport;
	}

	public void releaseUpdate(String version) {

		Log log = new Log(version);
		updateHistory[numUpdates] = log;
		numUpdates++;

	}

	public void addFix(String fix) {

	}
	
	public void submitRating(int rating) {
		
		double ratingAvg = 0;
		
		this.ratings[rating]++;
		this.ratingReport = "Average of " + + " ratings: " + ;
		
	}

	@Override
	public String toString() {
		return name + " (Current Version: " + getWhatIsNew() + "; Average Rating: " + avgRating + ")";
	}

}

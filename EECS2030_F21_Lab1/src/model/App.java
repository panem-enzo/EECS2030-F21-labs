package model;

public class App {

	int numRatings;
	int numUpdates = 0;
	int maxRatings = 15;
	int[] ratings = new int[5];
	final int MAX_UPDATE_LOGS = 20;
	String name;
	String ratingReport = "No ratings submitted so far!";
	String update = "n/a";
	String avgRating = "n/a";
	String currentVersion = "n/a";
	Log[] updateHistory = new Log[MAX_UPDATE_LOGS];

	public App(String name, int maxRatings) {

		this.name = name;
		this.maxRatings = maxRatings;

	}

	public String getName() {
		return this.name;
	}

	public String getWhatIsNew() {

		if (numUpdates > 0) {
			this.currentVersion = updateHistory[numUpdates - 1].toString();
		}

		return this.currentVersion;
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

		} else {
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

	public void submitRating(int rating) {

		ratings[rating - 1]++;

		double numAvgRating;
		int score1 = ratings[0], score2 = ratings[1], score3 = ratings[2], score4 = ratings[3], score5 = ratings[4];

		numRatings++;
		numAvgRating = (score1 + (score2 * 2.0) + (score3 * 3.0) + (score4 * 4.0) + (score5 * 5.0))/numRatings;
		this.avgRating = String.format("%.1f", numAvgRating);
		
		ratingReport = "Average of " + numRatings + " ratings: " + this.avgRating + " (Score 5: "
				+ score5 + ", Score 4: " + score4 + ", Score 3: " + score3 + ", Score 2: " + score2 + ", Score 1: "
				+ score1 + ")";
		
	}

	@Override
	public String toString() {
		return name + " (Current Version: " + getWhatIsNew() + "; Average Rating: " + avgRating + ")";
	}

}

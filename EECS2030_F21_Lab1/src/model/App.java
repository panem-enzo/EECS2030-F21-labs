package model;

public class App {

	String appName;
	int numUpdates;
	int numRatings;
	Log[] updates = new Log[20];
	int[] ratings;
	String avgStr = "n/a";

	public App(String appName, int maxRatings) {
		this.appName = appName;
		this.ratings = new int[maxRatings];
	}

	public String getName() {
		return this.appName;
	}

	public String getWhatIsNew() {

		String latestVersion = "n/a";

		if (numUpdates != 0) {
			latestVersion = updates[numUpdates-1].toString();
		}

		return latestVersion;
	}

	public Log[] getUpdateHistory() {

		Log[] updateHistory = new Log[numUpdates];

		for (int i = 0; i < numUpdates; i++) {
			updateHistory[i] = updates[i];
		}

		return updateHistory;
	}

	public void releaseUpdate(String versionNum) {
		updates[numUpdates] = new Log(versionNum);
		numUpdates++;
	}

	public Log getVersionInfo(String versionNum) {

		Log versionInfo = null;

		for (int i = 0; i < numUpdates; i++) {
			if (updates[i].getVersion().equals(versionNum)) {
				versionInfo = updates[i];
			}
		}

		return versionInfo;
	}

	public String getRatingReport() {

		String ratingReport = "No ratings submitted so far!";
		double sum = 0, average = 0;
		int score[] = new int[5];

		// "Average of 1 ratings: 3.0 (Score 5: 0, Score 4: 0, Score 3: 1, Score 2: 0,
		// Score 1: 0)"

		if (numRatings != 0) {

			for (int i = 0; i < numRatings; i++) {
				sum += ratings[i];

				if (ratings[i] == 1) {
					score[4]++;
				} else if (ratings[i] == 2) {
					score[3]++;
				} else if (ratings[i] == 3) {
					score[2]++;
				} else if (ratings[i] == 4) {
					score[1]++;
				} else if (ratings[i] == 5) {
					score[0]++;
				}
			}

			average = sum / numRatings;
			this.avgStr = String.format("%.1f", average);

			ratingReport = "Average of " + numRatings + " ratings: " + String.format("%.1f", average) + " (Score 5: "
					+ score[0] + "," + " Score 4: " + score[1] + "," + " Score 3: " + score[2] + "," + " Score 2: "
					+ score[3] + "," + " Score 1: " + score[4] + ")";
			
		} else {
			ratingReport = "No ratings submitted so far!";
		}
		
		return ratingReport;
		
	}

	public void submitRating(int rating) {
		ratings[numRatings] = rating;
		numRatings++;
	}

	@Override
	public String toString() {
		//"GoodNotes 5 (Current Version: n/a; Average Rating: n/a)\"
		
		return this.appName + " (Current Version: " + getWhatIsNew() + "; Average Rating: " + this.avgStr + ")";
	}

}

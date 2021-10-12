package model;

public class Log {

	String versionNum;
	String[] fixes = new String[10];
	int numFixes;

	public Log(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getVersion() {
		return this.versionNum;
	}

	public int getNumberOfFixes() {
		return this.numFixes;
	}

	public String getFixes() {

		String fixString = "[";

		for (int i = 0; i < numFixes; i++) {

			if (i == numFixes - 1) {
				fixString += fixes[i];
			} else {
				fixString += fixes[i] + ", ";
			}

		}

		fixString += "]";

		System.out.println(fixString);

		return fixString;
	}
	
	public void addFix(String fix) {
		this.fixes[numFixes] = fix;
		numFixes++;	
	}

	@Override
	public String toString() {
		return "Version " + getVersion() + " contains " + getNumberOfFixes() + " fixes " +  getFixes();
	}
	
	
}

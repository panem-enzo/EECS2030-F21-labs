package model;

import java.util.Arrays;

public class Log {

	int numFixes;
	final int MAX_FIXES = 10;
	String[] fixes = new String[MAX_FIXES];
	String version;
	
	public Log(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public int getNumberOfFixes() {
		
		return this.numFixes;
	}
	
	public String getFixes() {
		
		String fixString = "[";
		
		for (int i = 0; i < numFixes; i++) {
			
			if (numFixes == 0 || i == numFixes-1) {
				fixString += fixes[i];
			}
			else {
				fixString += fixes[i] + ", ";
			}
			
		}
		
		fixString += "]";
		
		return fixString;
	}
	
	public void addFix(String fix) {
		
		fixes[numFixes] = fix;
		numFixes++;
		
	}

	@Override
	public String toString() {
		return "Version " + version + " contains " + numFixes + " fixes " + getFixes();
	}
	
	
}

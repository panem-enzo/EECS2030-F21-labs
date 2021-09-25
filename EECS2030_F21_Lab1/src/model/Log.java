package model;

import java.util.Arrays;

public class Log {

	String version;
	String[] fixes;
	int numFixes;
	final int MAX_FIXES = 10;
	
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
			
			if (i > 0) {
				fixString += fixes[i] + ", ";
			}
			else {
				fixString += fixes[i];
			}
			
		}
		
		fixString += "]";
		
		return fixString;
	}
	
	public void addFix(String fix) {
		
		for (int i = 0; i < MAX_FIXES; i++) {
			
		}
		
		numFixes++;
	}

	@Override
	public String toString() {
		return "Version " + version + " contains " + numFixes + " fixes " + getFixes();
	}
	
	
}

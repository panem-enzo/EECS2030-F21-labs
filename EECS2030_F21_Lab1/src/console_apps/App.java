package console_apps;

public class App {

	public static void main(String[] args) {
		
		int numFixes = 3;
		String[] fixes = {"one","two","three"};
		
		String fixString = "[";
		
		for (int i=0; i < numFixes; i++) {
			
			if (i == numFixes-1) {
				fixString += fixes[i];
			} else {
				fixString += fixes[i] + ", ";
			}
			
		}
		
		fixString += "]";
		
		System.out.println(fixString);

	}

}

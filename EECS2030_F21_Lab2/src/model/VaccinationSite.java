package model;

public class VaccinationSite {

	private String vaccinationSite;
	private int limitDose;
	private int numDistribution;
	private int numEntries;
	private VaccineDistribution[] distribution;
	
	public VaccinationSite(String vaccinationSite, int limitDose) {
		this.vaccinationSite = vaccinationSite;
		this.limitDose = limitDose;
		this.distribution = new VaccineDistribution[limitDose];
		
	}
	
	public int getNumberOfAvailableDoses() {
		return 0;
	}
	
	public int getNumberOfAvailableDoses(String codename) {
		return 0;
	}
	
	public void addDistribution(Vaccine vaccine, int doses) throws UnrecognizedVaccineCodeNameException, TooMuchDistributionException {
		
		if (!(vaccine.getCodeName().equals("mRNA-1273") || vaccine.getCodeName().equals("BNT162b2") || vaccine.getCodeName().equals("Ad26.COV2.S") || vaccine.getCodeName().equals("AZD1222"))) {
			throw new UnrecognizedVaccineCodeNameException("The vaccine codename is not recognized.");
		}
		
		if ((numDistribution += doses) > limitDose) {
			throw new TooMuchDistributionException("Too much distribution, passes the limit.");
		}
		
		this.distribution[numEntries] = new VaccineDistribution(vaccine, doses);
		this.numEntries++;
	}
	
	public void bookAppointment(HealthRecord healthrecord) throws InsufficientVaccineDosesException {
		
	}
	
	public void administer(String date) {
		
	}
	
	public String getDistribution() {
		
		String distribution = "<";
		
		if (numEntries != 0) {
			
			VaccineDistribution[] trimVaccineDistribution = new VaccineDistribution[numDistribution];
			
			// Algorithm for number of distributions (FINISH IMPLEMENTING)
			
			for (int i = 0; i < numEntries; i++) {
				
				for (int j = 1; j < numEntries; j++) {
					
					if (this.distribution[j].equals(trimVaccineDistribution[i])) {
						this.distribution[i].addDoses(this.distribution[j].getDoses());
					} 
					
				}
	
			}
			
			for (int i = 0; i < numEntries; i++) {
				
				if (i == numEntries-1) {
					distribution += this.distribution[i].getDoses() + " doses of " + this.distribution[i].getVaccine().getManufacturer();
				} else {
					distribution += this.distribution[i].getDoses() + " doses of " + this.distribution[i].getVaccine().getManufacturer() + ", ";
				}
			}
			
		}
		
		distribution += ">";
		
		return distribution;
	}

	@Override
	public String toString() {
		
		return this.vaccinationSite + " has " + this.numDistribution + " available doses: " + getDistribution();
	}
	
	
}

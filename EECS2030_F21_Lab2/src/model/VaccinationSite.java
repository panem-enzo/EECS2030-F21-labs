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

	public void addDistribution(Vaccine vaccine, int doses)
			throws UnrecognizedVaccineCodeNameException, TooMuchDistributionException {

		if (!(vaccine.getCodeName().equals("mRNA-1273") || vaccine.getCodeName().equals("BNT162b2")
				|| vaccine.getCodeName().equals("Ad26.COV2.S") || vaccine.getCodeName().equals("AZD1222"))) {
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

			// Algorithm for number of distributions (FINISH IMPLEMENTING)

			int[] duplicate = new int[numEntries];
			int count = 0;

			// Finding index of duplicates **WORKS**

			for (int i = 0; i < numEntries; i++) {

				for (int j = i + 1; j < numEntries; j++) {
					if (this.distribution[i].getVaccine().getManufacturer()
							.equals(this.distribution[j].getVaccine().getManufacturer())) {
						duplicate[count] = j;
						count++;
					}
				}

			}

			int unique = numEntries - count, k = 0;
			VaccineDistribution[] newVaccineDistribution = new VaccineDistribution[numEntries];
			VaccineDistribution[] trimVaccineDistribution = new VaccineDistribution[unique];
			
			for (int i = 0; i < numEntries; i++) {
				newVaccineDistribution[i] = new VaccineDistribution(this.distribution[i].getVaccine(), this.distribution[i].getDoses());
			}
			
			//**IMPLEMENTATION NEEDS TO BE FIXED**
			
			if (count > 0) {

				//Combining duplicates with unique by locating their indices
				for (int i = 0; i < numEntries; i++) {

					for (int j = 0; j < count; j++) {
						
						if (newVaccineDistribution[i].getVaccine().getManufacturer().equals(
								newVaccineDistribution[duplicate[j]].getVaccine().getManufacturer()) && i != duplicate[j]) {
							newVaccineDistribution[i].addDoses(newVaccineDistribution[duplicate[j]].getDoses()); 
						}
						
					}
					
				}
				
				//Trimming array to only contain unique vaccine distributions with summed doses
				for (int i = 0; i < numEntries; i++) {
					
					boolean isDuplicate = false;
					
					for (int j = 0; j < count; j++) {
						if (i == duplicate[j]) {
							isDuplicate = true;
							break;
						}
					}
					
					if (isDuplicate == true) {
						continue;
					} else {
						trimVaccineDistribution[k] = newVaccineDistribution[i];
						k++;
					}

				}

			} else {
				trimVaccineDistribution = newVaccineDistribution;
			}
			 
			//IMPLEMENTATION WORKS
			for (int i = 0; i < unique; i++) {

				if (i == unique - 1) {
					distribution += trimVaccineDistribution[i].getDoses() + " doses of "
							+ trimVaccineDistribution[i].getVaccine().getManufacturer();
				} else {
					distribution += trimVaccineDistribution[i].getDoses() + " doses of "
							+ trimVaccineDistribution[i].getVaccine().getManufacturer() + ", ";
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

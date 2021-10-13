package model;

public class VaccinationSite {

	private String vaccinationSite;
	private int limitDose;
	private int numDistribution;
	private int numEntries;
	private int numAppointment;
	private int numNonDistributed;
	private VaccineDistribution[] distribution;
	private VaccineDistribution[] trimVaccineDistribution;
	private HealthRecord[] appointment;
	private boolean adminstered = false;

	public VaccinationSite(String vaccinationSite, int limitDose) {
		this.vaccinationSite = vaccinationSite;
		this.limitDose = limitDose;
		this.distribution = new VaccineDistribution[limitDose];
		this.trimVaccineDistribution = new VaccineDistribution[numEntries];
		this.appointment = new HealthRecord[200];

	}

	public int getNumberOfAvailableDoses() {
		return this.numDistribution;
	}

	public int getNumberOfAvailableDoses(String codename) {

		int dose = 0;
		
		for (int i = 0; i < getDistribution().length; i++) {
			if (getDistribution()[i].getVaccine().getCodeName().equals(codename)) {
				dose = getDistribution()[i].getDoses();
				break;
			}
		}
		
		return dose;
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

	public void bookAppointment(HealthRecord healthRecord) throws InsufficientVaccineDosesException {
		this.appointment[numAppointment] = healthRecord;
		this.numAppointment++;
		this.numNonDistributed++;
		
		if (this.numNonDistributed > this.numDistribution) {
			healthRecord.appointmentTest(false, this.vaccinationSite);
			throw new InsufficientVaccineDosesException(healthRecord.getAppointmentStatus());
		} else {
			healthRecord.appointmentTest(true, this.vaccinationSite);
		}
		
	}

	public void administer(String date) {
		
		int k = 0;
		this.adminstered = true;
		
		for (int i = 0; i < this.numAppointment; i++) {
			
			this.appointment[i].addRecord(this.trimVaccineDistribution[k].getVaccine(), this.vaccinationSite, date);
			this.trimVaccineDistribution[k].takeDose();
			this.numDistribution--;
			
			if (this.trimVaccineDistribution[k].getDoses() == 0) {
				k++;
			}
			
		}
		
	}

	public VaccineDistribution[] getDistribution() {

		// Algorithm for number of distributions (FINISH IMPLEMENTING)

		if (numEntries == 0) {
			VaccineDistribution[] none = new VaccineDistribution[0];
			return none;
		}
		
		if (this.adminstered == true) {
			return this.trimVaccineDistribution;
		}
		
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
			newVaccineDistribution[i] = new VaccineDistribution(this.distribution[i].getVaccine(),
					this.distribution[i].getDoses());
		}

		// **IMPLEMENTATION NEEDS TO BE FIXED**

		if (count > 0) {

			// Combining duplicates with unique by locating their indices
			for (int i = 0; i < numEntries; i++) {

				for (int j = 0; j < count; j++) {

					if (newVaccineDistribution[i].getVaccine().getManufacturer().equals(
							newVaccineDistribution[duplicate[j]].getVaccine().getManufacturer()) && i != duplicate[j]) {
						newVaccineDistribution[i].addDoses(newVaccineDistribution[duplicate[j]].getDoses());
					}

				}

			}

			// Trimming array to only contain unique vaccine distributions with summed doses
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
		
		this.trimVaccineDistribution = trimVaccineDistribution;
		
		return this.trimVaccineDistribution;
	}

	@Override
	public String toString() {
	
		String distribution = "<";
		
		for (int i = 0; i < getDistribution().length; i++) {
			if (i == getDistribution().length-1) {
				distribution += getDistribution()[i].getDoses() + " doses of " + getDistribution()[i].getVaccine().getManufacturer();
			} else {
				distribution += getDistribution()[i].getDoses() + " doses of " + getDistribution()[i].getVaccine().getManufacturer() + ", ";
			}
		}
		
		distribution += ">";
		
		return this.vaccinationSite + " has " + this.numDistribution + " available doses: " + distribution;
	}

}
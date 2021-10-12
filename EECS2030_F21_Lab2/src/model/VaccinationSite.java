package model;

public class VaccinationSite {

	private String vaccinationSite;
	private int limitDose;
	
	public VaccinationSite(String vaccinationSite, int limitDose) {
		this.vaccinationSite = vaccinationSite;
		this.limitDose = limitDose;
	}
	
	public int getNumberOfAvailableDoses() {
		return 0;
	}
	
	public int getNumberOfAvailableDoses(String codename) {
		return 0;
	}
	
	public void addDistribution(Vaccine vaccine, int doses) throws UnrecognizedVaccineCodeNameException, TooMuchDistributionException {
		
	}
	
	public void bookAppointment(HealthRecord healthrecord) throws InsufficientVaccineDosesException {
		
	}
	
	public void administer(String date) {
		
	}
}

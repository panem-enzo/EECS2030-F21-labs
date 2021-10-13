package model;

public class VaccineDistribution {

	private Vaccine vaccine;
	private int doses;

	public VaccineDistribution(Vaccine vaccine, int doses) {
		this.vaccine = vaccine;
		this.doses = doses;
	}
	
	public Vaccine getVaccine() {
		return this.vaccine;
	}

	public int getDoses() {
		return this.doses;
	}
	
	public void addDoses(int doses) {
		this.doses += doses;
	}


	@Override
	public String toString() {
		return this.doses + " doses of " + this.vaccine.getCodeName() + " by " + this.vaccine.getManufacturer();
	}

	
	
}

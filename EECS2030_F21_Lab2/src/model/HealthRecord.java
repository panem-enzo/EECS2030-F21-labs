package model;

public class HealthRecord {

	private String patient;
	private int limitDoses;
	private int numEntries;
	private int numAppointments;
	private String[] vaccinationHistory;

	public HealthRecord(String patient, int limitDoses) {
		this.patient = patient;
		this.limitDoses = limitDoses;
		this.vaccinationHistory = new String[this.limitDoses];
	}

	public String getVaccinationReceipt() {

		// "Number of doses Alan has received: 1 [Recognized vaccine: mRNA-1273 (RNA;
				// Moderna) in North York General Hospital on April-20-2021]"
				// Recognized vaccine: mRNA-1273 (RNA; Moderna) in North York General Hospital
				// on April-20-2021
		
		String vaccinationReceipt = this.patient + " has not yet received any doses.";
		
		if (this.numEntries != 0) {

			vaccinationReceipt = "Number of doses " + this.patient + " has received: " + this.numEntries
					+ " [";

			for (int i = 0; i < numEntries; i++) {

				if (i == numEntries - 1) {
					vaccinationReceipt += this.vaccinationHistory[i];
				} else {
					vaccinationReceipt += this.vaccinationHistory[i] + "; ";
				}

			}

			vaccinationReceipt += "]";

		}

		return vaccinationReceipt;

	}

	public String getAppointmentStatus() {

		String appointmentStatus = "No vaccination appointment for " + this.patient + " yet";
		return appointmentStatus;
		
	}

	public void addRecord(Vaccine vaccine, String vaccinationSite, String date) {

		this.vaccinationHistory[numEntries] = vaccine.toString() + " in " + vaccinationSite + " on " + date;
		this.numEntries++;

	}
}

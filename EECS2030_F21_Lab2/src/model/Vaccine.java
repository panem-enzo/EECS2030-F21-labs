package model;

public class Vaccine {

	private String codename;
	private String type;
	private String manufacturer;
	private String status;
	
	public Vaccine(String codename, String type, String manufacturer) {
		this.codename = codename;
		this.type = type;
		this.manufacturer = manufacturer;
		
		if (this.codename.equals("mRNA-1273") || this.codename.equals("BNT162b2") || this.codename.equals("Ad26.COV2.S") || this.codename.equals("AZD1222")) {
			this.status = "Recognized vaccine: " + this.codename + " (" + this.type + "; " + this.manufacturer + ")";
		} else {
			this.status = "Unrecognized vaccine: " + this.codename + " (" + this.type + "; " + this.manufacturer + ")";
		}
	}
	
	public String getCodeName() {
		return this.codename;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getManufacturer() {
		return this.manufacturer;
	}

	@Override
	public String toString() {
		return status;
	}
	
}

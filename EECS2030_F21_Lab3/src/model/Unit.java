package model;

public class Unit {

	private String function;
	private double width;
	private double length;
	private double area;
	private boolean defaultUnit;
	private final double conversion;

	public Unit(String function, double width, double length) {

		// All measurements are in feet by default
		this.function = function;
		this.width = width;
		this.length = length;
		this.area = width * length;
		this.defaultUnit = true;
		this.conversion = 0.3048;

	}

	public Unit(Unit other) {
		this(other.function, other.width, other.length);
	}

	public void toogleMeasurement() {

		if (defaultUnit) {
			// convert to meters
			this.width *= this.conversion;
			this.length *= this.conversion;
			this.area = this.width * this.length;
			defaultUnit = false;
		} else {
			// convert to feet
			this.width /= this.conversion;
			this.length /= this.conversion;
			this.area = this.width * this.length;
			defaultUnit = true;
		}

	}

	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		Unit other = (Unit) obj;
		return this.function == other.function && this.area == other.area;

	}

	public double getWidth() {
		return this.width;
	}

	public double getLength() {
		return this.length;
	}

	public double getArea() {
		return this.area;
	}

	public String getFunction() {
		return this.function;
	}

	@Override
	public String toString() {

		String output;

		if (defaultUnit) {
			output = String.format("A unit of %.0f square feet (%.0f' wide and %.0f' long) functioning as %s",
					this.area, this.width, this.length, this.function);
		} else {
			output = String.format("A unit of %.2f square meters (%.2f m wide and %.2f m long) functioning as %s",
					this.area, this.width, this.length, this.function);
		}

		return output;

	}

}

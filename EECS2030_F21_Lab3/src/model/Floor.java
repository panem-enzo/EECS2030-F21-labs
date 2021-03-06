package model;

public class Floor {

	private double capacity;
	private double utilizedSpace;
	private int nou;
	private Unit[] units;

	public Floor(double capacity) {

		this.units = new Unit[20];
		this.capacity = capacity;

	}

	public Floor(Floor other) {

		this(other.capacity);

		this.utilizedSpace = other.utilizedSpace;
		this.nou = other.nou;

		for (int i = 0; i < nou; i++) {
			this.units[i] = new Unit(other.units[i].getFunction(), other.units[i].getWidth(),
					other.units[i].getLength());
		}

	}

	public void addUnit(String function, double width, double length) throws InsufficientFloorSpaceException {

		Unit temp = new Unit(function, width, length);

		if (capacity - temp.getArea() < 0) {
			throw new InsufficientFloorSpaceException("Insufficient Floor Space.");
		} else {

			this.units[nou] = temp;
			this.capacity -= this.units[nou].getArea();
			this.utilizedSpace += this.units[nou].getArea();

			nou++;
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

		Floor other = (Floor) obj;

		boolean utilizedTheSame = true;

		// Check how many instances of the current unit exist in the same array
		// (including itself)
		for (int i = 0; i < this.nou && utilizedTheSame; i++) {

			int currentCount = 1, otherCount = 0;

			for (int j = 0; j < this.nou; j++) {

				// avoids the index of this(unit)
				if (j != i) {
					if (this.units[i].equals(this.units[j])) {
						currentCount++;
					}
				}

			}

			// Check which ones in this array are equal to other array
			for (int k = 0; k < this.nou; k++) {

				if (this.units[i].equals(other.getUnits()[k])) {
					otherCount++;
				}

			}

			if (currentCount != otherCount) {
				utilizedTheSame = false;
			}

		}

		return this.capacity == other.capacity && utilizedTheSame;

	}

	public Unit[] getUnits() {
		return this.units;
	}

	@Override
	public String toString() {

		String output = String.format("Floor's utilized space is %.0f sq ft (%.0f sq ft remaining): [",
				this.utilizedSpace, this.capacity);

		for (int i = 0; i < this.nou; i++) {

			output += String.format("%s: %.0f sq ft (%.0f' by %.0f')", this.units[i].getFunction(),
					this.units[i].getArea(), this.units[i].getWidth(), this.units[i].getLength());

			if (i != this.nou - 1) {
				output += ", ";
			}

		}

		output += "]";

		return output;
	}

}

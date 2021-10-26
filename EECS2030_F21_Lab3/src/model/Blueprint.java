package model;

public class Blueprint {

	private int nof; //num of floors added
	private int numFloor; // num of floors planned to add
	private Floor[] floors;
	
	public Blueprint(int numFloor) {
		this.numFloor = numFloor;
		this.floors = new Floor[numFloor];
	}
	
	//copy constructor
	public Blueprint(Blueprint other) {
		
		this (other.numFloor);
		this.nof = other.nof;
		
		for (int i = 0; i < this.nof; i ++) {
			this.floors[i] = new Floor(other.floors[i]);
		}
		
	}
	 
	public void addFloorPlan(Floor floor) {
		this.floors[nof] = new Floor(floor);
		this.nof ++;
	}
	
	public Floor[] getFloors() {
		
		Floor[] newFloors = new Floor[nof];
		
		for (int i = 0; i < nof; i ++) { 
			newFloors[i] = new Floor(this.floors[i]); 
		}
		
		return newFloors;
	}
	
//	public int getNumFloorsAdded() {
//		return this.nof;
//	}
// 
	@Override
	public String toString() {
		
		return String.format("%.1f percents of building blueprint completed (%d out of %d floors)", ((double) this.nof / this.numFloor) * 100, this.nof, this.numFloor);
	}
	
	
}

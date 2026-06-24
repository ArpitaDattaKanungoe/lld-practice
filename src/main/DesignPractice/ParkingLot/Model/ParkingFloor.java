package main.DesignPractice.ParkingLot.Model;

import java.util.List;

public class ParkingFloor {
  private final int floorNumber;

  private final List<ParkingSpot> parkingSpots;

  public ParkingFloor(int floorNumber, List<ParkingSpot> parkingSpots) {
    this.floorNumber = floorNumber;
    this.parkingSpots = parkingSpots;
  }

  public List<ParkingSpot> getParkingSpots() {
    return parkingSpots;
  }
}

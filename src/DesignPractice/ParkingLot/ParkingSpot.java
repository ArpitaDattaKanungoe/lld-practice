package DesignPractice.ParkingLot;

import DesignPractice.ParkingLot.Vehicle.Vehicle;
import DesignPractice.ParkingLot.Vehicle.VehicleType;

/**
 * ParkingSpot represents one spot only
 */
public class ParkingSpot {

  private final int spotId;
  private static int counter = 0;
  private Vehicle currentVehicle;
  private final VehicleType allowedVehicleType;
  private boolean occupied;

  public ParkingSpot(VehicleType allowedVehicleType) {
    this.spotId = ++counter;
    this.currentVehicle = null;
    this.allowedVehicleType = allowedVehicleType;
    this.occupied = false;
  }

  public boolean isOccupied() {
    return occupied;
  }

  public VehicleType getAllowedVehicleType() {
    return allowedVehicleType;
  }

  public void parkInSpot(Vehicle vehicle) {
    if (vehicle.getVehicleType() == allowedVehicleType && !occupied) {
      occupied = true;
      this.currentVehicle = vehicle;
      System.out.println("Vehicle number " + vehicle.getVehicleNumber() + " is parked");
    } else if (occupied) {
      System.out.println("Spot is occupied");
    } else {
      System.out.println("Vehicle type mismatched");
    }
  }

  public void freeSpot() {
    occupied = false;
    this.currentVehicle = null;
    System.out.println("Spot: " + spotId + " is freed up");
  }
}

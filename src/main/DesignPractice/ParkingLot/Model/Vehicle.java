package main.DesignPractice.ParkingLot.Model;

public abstract class Vehicle {

  private final String vehicleNumber;

  protected Vehicle(String vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
  }

  public String getVehicleNumber() {
    return vehicleNumber;
  }

  public abstract VehicleType getVehicleType();
}

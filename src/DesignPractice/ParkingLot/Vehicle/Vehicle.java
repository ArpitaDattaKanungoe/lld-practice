package DesignPractice.ParkingLot.Vehicle;

public abstract class Vehicle {

  private VehicleType vehicleType;
  private String vehicleNumber;

  public Vehicle(String vehicleNumber, VehicleType vehicleType) {
    this.vehicleNumber = vehicleNumber;
    this.vehicleType = vehicleType;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public String getVehicleNumber() {
    return vehicleNumber;
  }
}

package DesignPractice.ParkingLot.Model;

public class Bike extends Vehicle {

  public Bike(String vehicleNumber) {
    super(vehicleNumber);
  }

  @Override
  public VehicleType getVehicleType() {
    return VehicleType.BIKE;
  }
}

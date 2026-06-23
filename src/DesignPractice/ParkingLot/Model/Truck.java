package DesignPractice.ParkingLot.Model;

public class Truck extends Vehicle {

  public Truck(String vehicleNumber) {
    super(vehicleNumber);
  }

  @Override
  public VehicleType getVehicleType() {
    return VehicleType.TRUCK;
  }
}

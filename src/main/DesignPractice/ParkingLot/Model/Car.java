package main.DesignPractice.ParkingLot.Model;

public class Car extends Vehicle {

  public Car(String vehicleNumber) {
    super(vehicleNumber);
  }

  @Override
  public VehicleType getVehicleType() {
    return VehicleType.CAR;
  }
}

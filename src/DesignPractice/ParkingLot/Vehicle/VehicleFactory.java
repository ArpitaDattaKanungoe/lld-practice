package DesignPractice.ParkingLot.Vehicle;

import static DesignPractice.ParkingLot.Vehicle.VehicleType.bike;
import static DesignPractice.ParkingLot.Vehicle.VehicleType.car;
import static DesignPractice.ParkingLot.Vehicle.VehicleType.publicTransport;

public class VehicleFactory {
  public Vehicle createVehicle(String vehicleNumber, VehicleType vehicleType) { // composition (has-a)
    if (vehicleType == car) {
      return new Car(vehicleNumber, car);
    }
    if (vehicleType == bike) {
      return new Bike(vehicleNumber, bike);
    }
    if (vehicleType == publicTransport) {
      return new PublicTransport(vehicleNumber, publicTransport);
    }
    return null;
  }
}

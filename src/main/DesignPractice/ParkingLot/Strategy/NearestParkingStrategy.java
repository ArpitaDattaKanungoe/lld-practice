package main.DesignPractice.ParkingLot.Strategy;

import main.DesignPractice.ParkingLot.Model.ParkingFloor;
import main.DesignPractice.ParkingLot.Model.ParkingLot;
import main.DesignPractice.ParkingLot.Model.ParkingSpot;
import main.DesignPractice.ParkingLot.Model.Vehicle;
import main.DesignPractice.ParkingLot.CustomExceptions.ParkingLotFullException;

public class NearestParkingStrategy implements ParkingStrategy {

  private ParkingLot parkingLot;

  public NearestParkingStrategy(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }

  @Override
  public ParkingSpot findParkingSpot(Vehicle vehicle) {
    for (ParkingFloor floor : parkingLot.getParkingFloors()) {

      for (ParkingSpot spot : floor.getParkingSpots()) {

        if (spot.canFit(vehicle)) {
          return spot;
        }
      }
    }

    throw new ParkingLotFullException("No parking spot available.");
  }
}

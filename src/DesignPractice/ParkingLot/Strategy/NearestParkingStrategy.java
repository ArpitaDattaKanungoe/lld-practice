package DesignPractice.ParkingLot.Strategy;

import DesignPractice.ParkingLot.Model.ParkingFloor;
import DesignPractice.ParkingLot.Model.ParkingLot;
import DesignPractice.ParkingLot.Model.ParkingSpot;
import DesignPractice.ParkingLot.Model.Vehicle;
import DesignPractice.ParkingLot.CustomExceptions.ParkingLotFullException;

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

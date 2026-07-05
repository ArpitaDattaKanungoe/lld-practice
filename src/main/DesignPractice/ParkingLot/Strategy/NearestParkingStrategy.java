package main.DesignPractice.ParkingLot.Strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import main.DesignPractice.ParkingLot.CustomExceptions.ParkingLotFullException;
import main.DesignPractice.ParkingLot.Model.ParkingFloor;
import main.DesignPractice.ParkingLot.Model.ParkingLot;
import main.DesignPractice.ParkingLot.Model.ParkingSpot;
import main.DesignPractice.ParkingLot.Model.Vehicle;

public class NearestParkingStrategy implements ParkingStrategy {

  private ParkingLot parkingLot;

  public NearestParkingStrategy(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }

  //Nearest strategy checks lower floor numbers first.
  @Override
  public ParkingSpot findParkingSpot(Vehicle vehicle) {
    List<ParkingFloor> floors = new ArrayList<>(parkingLot.getParkingFloors());
    floors.sort(Comparator.comparingInt(ParkingFloor::getFloorNumber));

    for (ParkingFloor floor : floors) {

      for (ParkingSpot spot : floor.getParkingSpots()) {

        if (spot.canFit(vehicle)) {
          return spot;
        }
      }
    }

    throw new ParkingLotFullException("No parking spot available.");
  }
}

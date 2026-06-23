package DesignPractice.ParkingLot.Strategy;

import DesignPractice.ParkingLot.Model.ParkingSpot;
import DesignPractice.ParkingLot.Model.Vehicle;

public interface ParkingStrategy {

  ParkingSpot findParkingSpot(Vehicle vehicle);
}

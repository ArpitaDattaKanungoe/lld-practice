package main.DesignPractice.ParkingLot.Strategy;

import main.DesignPractice.ParkingLot.Model.ParkingSpot;
import main.DesignPractice.ParkingLot.Model.Vehicle;

public interface ParkingStrategy {

  ParkingSpot findParkingSpot(Vehicle vehicle);
}

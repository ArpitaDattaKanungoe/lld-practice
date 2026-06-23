package DesignPractice.ParkingLot.Model;

import java.util.List;

public class ParkingLot {
  private final String id;
  private final String name;

  private final List<ParkingFloor> parkingFloors;

  private final List<EntryGate> entryGates;

  private final List<ExitGate> exitGates;

  public ParkingLot(String id, String name, List<ParkingFloor> parkingFloors, List<EntryGate> entryGates,
      List<ExitGate> exitGates) {
    this.id = id;
    this.name = name;
    this.parkingFloors = parkingFloors;
    this.entryGates = entryGates;
    this.exitGates = exitGates;
  }

  public List<ParkingFloor> getParkingFloors() {
    return parkingFloors;
  }
}

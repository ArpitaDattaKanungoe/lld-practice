package main.DesignPractice.ParkingLot.Model;

import java.util.ArrayList;
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
    this.parkingFloors = new ArrayList<>(parkingFloors);
    this.entryGates = new ArrayList<>(entryGates);
    this.exitGates = new ArrayList<>(exitGates);
  }

  public List<ParkingFloor> getParkingFloors() {
    return parkingFloors;
  }

  public EntryGate getEntryGate(String gateId) {
    return entryGates.stream()
        .filter(gate -> gate.getGateId().equals(gateId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid entry gate: " + gateId));
  }

  public ExitGate getExitGate(String gateId) {
    return exitGates.stream()
        .filter(gate -> gate.getGateId().equals(gateId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid exit gate: " + gateId));
  }

  public void addEntryGate(EntryGate entryGate) {
    entryGates.add(entryGate);
  }

  public void addExitGate(ExitGate exitGate) {
    exitGates.add(exitGate);
  }
}

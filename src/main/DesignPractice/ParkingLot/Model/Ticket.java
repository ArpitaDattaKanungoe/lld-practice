package main.DesignPractice.ParkingLot.Model;

import java.time.LocalDateTime;

public class Ticket {
  private final String ticketId;

  private final Vehicle vehicle;

  private final ParkingSpot parkingSpot;

  private final LocalDateTime entryTime;

  public Ticket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot, LocalDateTime entryTime) {
    this.ticketId = ticketId;
    this.vehicle = vehicle;
    this.parkingSpot = parkingSpot;
    this.entryTime = entryTime;
  }

  public String getTicketId() {
    return ticketId;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public ParkingSpot getParkingSpot() {
    return parkingSpot;
  }

  public LocalDateTime getEntryTime() {
    return entryTime;
  }
}

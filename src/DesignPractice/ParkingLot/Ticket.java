package DesignPractice.ParkingLot;

import DesignPractice.ParkingLot.Vehicle.Vehicle;
import java.time.Instant;

/**
 * made `vehicle`, `spot`, and `entryTime` as `final` because in a `Ticket`
 */
public class Ticket {
  private final int ticketId;
  private static int counter = 0;
  private final Vehicle vehicle;
  private final ParkingSpot spot;
  private final Instant entryTime;
  private Instant exitTime;
//  private Payment payment;

  public Ticket(Vehicle vehicle, ParkingSpot spot
//      , Payment payment
  ) {
    this.ticketId = ++counter;
    this.vehicle = vehicle;
    this.spot = spot;
    this.entryTime = Instant.now();
    System.out.println("Ticket has been created for vehicle: " + vehicle.getVehicleNumber() + " at : " + entryTime);
//    this.payment = payment;
  }

  public int getTicketId() {
    return ticketId;
  }
  // t.getTicketId() -> getTicketId(t)

  // def get_ticket_id(self):

  public Vehicle getVehicle() {
    return vehicle;
  }

  public ParkingSpot getSpot() {
    return spot;
  }

  public Instant getEntryTime() {
    return entryTime;
  }

  public Instant getExitTime() {
    return exitTime;
  }

  public void exitTime() {
    this.exitTime = Instant.now();
    System.out.println("Exit time for vehicle: " + vehicle.getVehicleNumber() + " is : " + exitTime);

  }
}

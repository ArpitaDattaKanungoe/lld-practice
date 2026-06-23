package DesignPractice.ParkingLot.Service;

import DesignPractice.ParkingLot.Model.ParkingSpot;
import DesignPractice.ParkingLot.Model.Ticket;
import DesignPractice.ParkingLot.Model.Vehicle;
import DesignPractice.ParkingLot.Repository.TicketRepository;
import java.time.LocalDateTime;
import java.util.UUID;

public class TicketService {
  //Generate Ticket
  //Find Ticket
  //Delete Ticket

  private final TicketRepository ticketRepository;

  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public Ticket generateTicket(Vehicle vehicle,
      ParkingSpot parkingSpot) {

    Ticket ticket = new Ticket(
        UUID.randomUUID().toString(),
        vehicle,
        parkingSpot,
        LocalDateTime.now()
    );

    ticketRepository.save(ticket);

    return ticket;
  }

  public Ticket getTicket(String ticketId) {

    Ticket ticket = ticketRepository.findById(ticketId);

    if (ticket == null) {
      throw new RuntimeException("Invalid Ticket");
    }

    return ticket;
  }

  public void removeTicket(String ticketId) {
    ticketRepository.delete(ticketId);
  }
}

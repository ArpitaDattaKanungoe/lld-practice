package main.DesignPractice.ParkingLot.Repository;

import main.DesignPractice.ParkingLot.Model.Ticket;
import java.util.HashMap;
import java.util.Map;

public class TicketRepository {

  private final Map<String, Ticket> ticketMap = new HashMap<>();

  public void save(Ticket ticket) {
    ticketMap.put(ticket.getTicketId(), ticket);
  }

  public Ticket findById(String ticketId) {
    return ticketMap.get(ticketId);
  }

  public void delete(String ticketId) {
    ticketMap.remove(ticketId);
  }
}

package DesignPractice.ParkingLot.Service;

import DesignPractice.ParkingLot.Model.ParkingSpot;
import DesignPractice.ParkingLot.Model.Ticket;
import DesignPractice.ParkingLot.Model.Vehicle;
import DesignPractice.ParkingLot.Strategy.ParkingStrategy;
import DesignPractice.ParkingLot.Strategy.PaymentStrategy;

public class ParkingLotService {
//Park a vehicle
//Remove a vehicle
//Call ParkingStrategy to find a spot
//Assign/remove vehicle from the parking spot
//Call TicketService
//Call PaymentService

  private final ParkingStrategy parkingStrategy;
  private final TicketService ticketService;
  private final PaymentService paymentService;

  public ParkingLotService(ParkingStrategy parkingStrategy,
      TicketService ticketService,
      PaymentService paymentService) {

    this.parkingStrategy = parkingStrategy;
    this.ticketService = ticketService;
    this.paymentService = paymentService;
  }

  public Ticket parkVehicle(Vehicle vehicle) {
    try {
      ParkingSpot parkingSpot = parkingStrategy.findParkingSpot(vehicle);
      parkingSpot.assignVehicle(vehicle);
      return ticketService.generateTicket(vehicle, parkingSpot);
    } catch (IllegalArgumentException e) {
      System.out.println("Parking lot is full. Cannot park vehicle.");
      return null;
    }

  }

  public void removeVehicle(String ticketId, PaymentStrategy paymentStrategy) {
    try {
      Ticket ticket = ticketService.getTicket(ticketId);
      double amount = paymentService.calculateAmount(ticket);
      paymentService.makePayment(amount, paymentStrategy);
      ParkingSpot parkingSpot = ticket.getParkingSpot();
      parkingSpot.removeVehicle();
      ticketService.removeTicket(ticketId);
    } catch (RuntimeException e) {
      System.out.println("Invalid ticket. Please check the ticket ID.");
    }
  }

}

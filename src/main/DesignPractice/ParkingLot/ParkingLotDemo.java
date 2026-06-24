package main.DesignPractice.ParkingLot;

import main.DesignPractice.ParkingLot.Model.Car;
import main.DesignPractice.ParkingLot.Model.EntryGate;
import main.DesignPractice.ParkingLot.Model.ExitGate;
import main.DesignPractice.ParkingLot.Model.ParkingFloor;
import main.DesignPractice.ParkingLot.Model.ParkingLot;
import main.DesignPractice.ParkingLot.Model.ParkingSpot;
import main.DesignPractice.ParkingLot.Model.Ticket;
import main.DesignPractice.ParkingLot.Model.Vehicle;
import main.DesignPractice.ParkingLot.Model.VehicleType;
import main.DesignPractice.ParkingLot.Repository.TicketRepository;
import main.DesignPractice.ParkingLot.Service.ParkingLotService;
import main.DesignPractice.ParkingLot.Service.PaymentService;
import main.DesignPractice.ParkingLot.Service.TicketService;
import main.DesignPractice.ParkingLot.Strategy.CashPaymentStrategy;
import main.DesignPractice.ParkingLot.Strategy.NearestParkingStrategy;
import main.DesignPractice.ParkingLot.Strategy.ParkingStrategy;
import main.DesignPractice.ParkingLot.Strategy.PaymentStrategy;
import java.util.List;

public class ParkingLotDemo {

  public static void main(String[] args) {

    // Create Parking Spots
    ParkingSpot bikeSpot = new ParkingSpot("B1", VehicleType.BIKE);
    ParkingSpot carSpot = new ParkingSpot("C1", VehicleType.CAR);

    // Gates
    EntryGate entryGate = new EntryGate("E1");
    ExitGate exitGate = new ExitGate("X1");

    // Create Floor
    ParkingFloor floor1 = new ParkingFloor(
        1,
        List.of(bikeSpot, carSpot)
    );

    // Create Parking Lot
    ParkingLot parkingLot = new ParkingLot(
        "P1",
        "City Mall Parking",
        List.of(floor1),
        List.of(entryGate),
        List.of(exitGate)
    );

    // Create Repository
    TicketRepository ticketRepository = new TicketRepository();

    // Create Strategies
    ParkingStrategy parkingStrategy = new NearestParkingStrategy(parkingLot);

    PaymentStrategy paymentStrategy = new CashPaymentStrategy();

    // Create Services
    TicketService ticketService = new TicketService(ticketRepository);

    PaymentService paymentService = new PaymentService();

    ParkingLotService parkingLotService =
        new ParkingLotService(
            parkingStrategy,
            ticketService,
            paymentService
        );

    // Vehicle Arrives
    Vehicle vehicle1 = new Car("ADK");
    Vehicle vehicle2 = new Car("DL");

    Ticket ticket = parkingLotService.parkVehicle(entryGate,vehicle1);

    System.out.println("Vehicle Parked");
    System.out.println("Ticket is " + ticket.getTicketId());

    Ticket ticket2 = parkingLotService.parkVehicle(entryGate,vehicle2);

    if(ticket2!=null){
      System.out.println("Vehicle Parked");
      System.out.println("Ticket is " + ticket2.getTicketId());
    }

    // Vehicle Exits
    parkingLotService.removeVehicle(exitGate, ticket.getTicketId(), paymentStrategy);

    System.out.println("Vehicle Removed");
  }
}
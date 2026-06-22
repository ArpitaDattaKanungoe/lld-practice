package DesignPractice.ParkingLot;

import static java.lang.Math.ceil;

import DesignPractice.ParkingLot.Vehicle.Vehicle;
import DesignPractice.ParkingLot.Vehicle.VehicleType;
import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ParkingLot {

  private final Map<VehicleType, List<ParkingSpot>> map = new HashMap<>();
  private final Map<VehicleType, Queue<ParkingSpot>> availableSpotMap = new HashMap<>(); // we can search in 0(1)

  public ParkingLot() {
    List<ParkingSpot> carSpots = addSpots(VehicleType.car, 3);
    map.put(VehicleType.car, carSpots);
    List<ParkingSpot> bikeSpots = addSpots(VehicleType.bike, 2);
    map.put(VehicleType.bike, bikeSpots);
    map.put(VehicleType.publicTransport, addSpots(VehicleType.publicTransport, 1));
    Queue<ParkingSpot> carAvailSpot = addSpotInQueue(VehicleType.car, 5);
    availableSpotMap.put(VehicleType.car,carAvailSpot);
  }

  private Queue<ParkingSpot> addSpotInQueue(VehicleType type, int count) {
    Queue<ParkingSpot> spots = new LinkedList<>();
    for (int i = 0; i < count; i++) {
      spots.offer(new ParkingSpot(type));
    }
    return spots;
  }

  private List<ParkingSpot> addSpots(VehicleType type, int count) {
    List<ParkingSpot> spots = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      spots.add(new ParkingSpot(type));
    }
    return spots;
  }

  public Ticket parkVehicle(Vehicle vehicle) { //o(n)
    //Spot available?
    VehicleType vehicleType = vehicle.getVehicleType();
    List<ParkingSpot> parkingSpots = map.get(vehicleType);
    if (parkingSpots == null) {
      System.out.println("No parkingSpot");
      return null;
    }
    for (ParkingSpot spot : parkingSpots) {
      if (!spot.isOccupied()) {
        // Spot assign
        spot.parkInSpot(vehicle);
        // get Ticket
        Ticket t1 = new Ticket(vehicle, spot);
        System.out.println("ticket : " + t1.getTicketId());
        return t1;
      }
    }
    return null;
  }

  public Ticket parkVehicle2(Vehicle vehicle) {
    //Spot available?
    VehicleType vehicleType = vehicle.getVehicleType();
    Queue<ParkingSpot> parkingSpotQueue = availableSpotMap.get(vehicleType);
    if (parkingSpotQueue.isEmpty()) {
      System.out.println("No parkingSpot");
      return null;
    }
    ParkingSpot spot = parkingSpotQueue.poll();
      // Spot assign
      spot.parkInSpot(vehicle);
      // get Ticket
      Ticket t1 = new Ticket(vehicle, spot);
      System.out.println("ticket : " + t1.getTicketId());
      return t1;
  }

  //Free spot
  public void unparkVehicle(Ticket ticket) { // o(n)
    if (ticket != null) {
      ParkingSpot spot = ticket.getSpot();
      ticket.exitTime();
      spot.freeSpot();
    }
  }

  //Free spot
  public void unparkVehicle2(Ticket ticket) {
    if (ticket != null) {
      ticket.exitTime();
      ParkingSpot spot = ticket.getSpot();
      spot.freeSpot();
      availableSpotMap.get(spot.getAllowedVehicleType()).offer(spot);
    }
  }

  public double calculatePrice(Ticket ticket) {
    int pricePerHour = ticket.getVehicle().getVehicleType().getChargePerHour();
    long minutes = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
    double hours = Math.max(1, ceil(minutes / 60.0));
    double totalPrice = hours * pricePerHour;
    System.out.println("total price for ticket : " + ticket.getTicketId() + " is : " + totalPrice);
    return totalPrice;
  }

  public int availableSpotCount(VehicleType type) { // o(n)
    List<ParkingSpot> spots = map.get(type);
    if (spots == null) {
      return 0;
    }
    int count = 0;
    for (ParkingSpot spot : spots) {
      if (!spot.isOccupied()) {
        count++;
      }
    }
    return count;
  }

  public int availableSpotInQueue(VehicleType type) {
    Queue<ParkingSpot> spots = availableSpotMap.get(type);
    if(spots.isEmpty()){
      System.out.println("No spots for Vehicle Type : "+ type);
      return 0;
    }
    return spots.size();
  }
}

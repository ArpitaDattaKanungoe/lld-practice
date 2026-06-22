package DesignPractice.ParkingLot.Vehicle;

import static DesignPractice.ParkingLot.Vehicle.VehicleType.car;

import DesignPractice.ParkingLot.ParkingLot;
import DesignPractice.ParkingLot.Ticket;

public class main {

  public static void main(String[] args) throws InterruptedException {
    VehicleFactory vehiclefactory = new VehicleFactory();

    Vehicle newCar = vehiclefactory.createVehicle("car1", car); // factory pattern
    Vehicle car1 = new Car("123", car);
    Vehicle car2 = new Car("234", car);
    Vehicle car3 = new Car("345", car);
    Vehicle car4 = new Car("456", car);
    ParkingLot parkingLot = new ParkingLot();
    Ticket ticket1 = parkingLot.parkVehicle2(newCar);
    System.out.println("available spot : " + parkingLot.availableSpotInQueue(car));
    Thread.sleep(8500);
    parkingLot.unparkVehicle2(ticket1);
    parkingLot.calculatePrice(ticket1);
    //payment
    System.out.println("available spot : " + parkingLot.availableSpotInQueue(car));
  }
}

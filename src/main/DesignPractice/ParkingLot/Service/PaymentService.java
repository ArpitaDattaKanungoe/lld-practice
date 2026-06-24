package main.DesignPractice.ParkingLot.Service;

import main.DesignPractice.ParkingLot.Model.Ticket;
import main.DesignPractice.ParkingLot.Strategy.PaymentStrategy;
import java.time.Duration;
import java.time.LocalDateTime;

public class PaymentService {
//Calculate parking amount
//Process payment using PaymentStrategy

    public double calculateAmount(Ticket ticket) {

      long hours = Duration.between(
          ticket.getEntryTime(),
          LocalDateTime.now()).toHours();

      hours = Math.max(1, hours);

      return hours *
             ticket.getVehicle()
                 .getVehicleType()
                 .getHourlyRate();
    }

    public void makePayment(double amount, PaymentStrategy paymentStrategy) {
      paymentStrategy.pay(amount);
    }
}

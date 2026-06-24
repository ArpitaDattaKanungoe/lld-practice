package main.DesignPractice.ParkingLot.Model;

public class Payment {
  private final String paymentId;
  private final double amount;
  private final PaymentMode paymentMode;

  public Payment(String paymentId, double amount, PaymentMode paymentMode) {
    this.paymentId = paymentId;
    this.amount = amount;
    this.paymentMode = paymentMode;
  }
}

package main.DesignPractice.BookingSystem.model;

public class payment {
  private String paymentId;

  private String reservationId;

  private double amount;

  private paymentStatus status;

  public payment(String paymentId, String reservationId, double amount, paymentStatus status) {
    this.paymentId = paymentId;
    this.reservationId = reservationId;
    this.amount = amount;
    this.status = status;
  }

  public paymentStatus getStatus() {
    return status;
  }

  public String getReservationId() {
    return reservationId;
  }

  public String getPaymentId() {
    return paymentId;
  }
}

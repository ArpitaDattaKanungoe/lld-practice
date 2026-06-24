package main.DesignPractice.BookingSystem.model;

public class booking {

  private String bookingId;

  private String reservationId;

  private String paymentId;

  private bookingStatus status;

  public booking(String bookingId, String reservationId, String paymentId, bookingStatus status) {
    this.bookingId = bookingId;
    this.reservationId = reservationId;
    this.paymentId = paymentId;
    this.status = status;
  }
}

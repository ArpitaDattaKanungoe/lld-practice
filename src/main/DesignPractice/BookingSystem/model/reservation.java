package main.DesignPractice.BookingSystem.model;

import java.time.LocalDateTime;

public class reservation {

  private String reservationId;

  private String seatId;

  private String userId;

  private reservationStatus status;

  private LocalDateTime expiresAt;

  public reservation(String reservationId, String seatId, String userId, reservationStatus status,
      LocalDateTime expiresAt) {
    this.reservationId = reservationId;
    this.seatId = seatId;
    this.userId = userId;
    this.status = status;
    this.expiresAt = expiresAt;
  }

  public String getSeatId() {
    return seatId;
  }

  public String getUserId() {
    return userId;
  }

  public reservationStatus getStatus() {
    return status;
  }

  public LocalDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setStatus(reservationStatus status) {
    this.status = status;
  }

  public String getReservationId() {
    return reservationId;
  }
}

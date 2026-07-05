package main.DesignPractice.BookingSystem.model;

public class seat {
  private String seatId;

  private String showId;

  private String seatNumber;
  private SeatStatus status;

  public seat(String seatId, String showId, String seatNumber, SeatStatus status) {
    this.seatId = seatId;
    this.showId = showId;
    this.seatNumber = seatNumber;
    this.status = status;
  }

  public String getSeatId() {
    return seatId;
  }

  public String getShowId() {
    return showId;
  }

  public String getSeatNumber() {
    return seatNumber;
  }

  public SeatStatus getStatus() {
    return status;
  }

  public void setStatus(SeatStatus status) {
    this.status = status;
  }
}

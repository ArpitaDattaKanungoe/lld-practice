package DesignPractice.BookingSystem.model;

public class seat {
  private String seatId;

  private String showId;

  private String seatNumber;
  private SeatStatus status;

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

  public void setShowId(String showId) {
    this.showId = showId;
  }

  public void setSeatNumber(String seatNumber) {
    this.seatNumber = seatNumber;
  }

  public void setStatus(SeatStatus status) {
    this.status = status;
  }
}

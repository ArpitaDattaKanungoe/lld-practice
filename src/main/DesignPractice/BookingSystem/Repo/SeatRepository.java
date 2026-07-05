package main.DesignPractice.BookingSystem.Repo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import main.DesignPractice.BookingSystem.model.seat;

public class SeatRepository {

  protected final Map<String, seat> seats = new ConcurrentHashMap<>();

  public void save(seat seat) {
    seats.put(seat.getSeatId(), seat);
  }

  public seat findById(String showSeatId) {
    return seats.get(showSeatId);
  }

  public void update(seat seat) {
    seats.put(seat.getSeatId(), seat);
  }
}

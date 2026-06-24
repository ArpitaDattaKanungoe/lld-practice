package main.DesignPractice.BookingSystem.Repo;

import main.DesignPractice.BookingSystem.model.seat;

public interface SeatRepository {
  seat findById(String showSeatId);
  void update(seat seat);
}

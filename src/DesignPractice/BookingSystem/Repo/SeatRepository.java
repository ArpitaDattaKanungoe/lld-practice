package DesignPractice.BookingSystem.Repo;

import DesignPractice.BookingSystem.model.seat;

public interface SeatRepository {
  seat findById(String showSeatId);
  void update(seat seat);
}

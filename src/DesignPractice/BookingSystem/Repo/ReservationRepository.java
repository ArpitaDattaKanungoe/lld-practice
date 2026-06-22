package DesignPractice.BookingSystem.Repo;

import DesignPractice.BookingSystem.model.reservation;
import DesignPractice.BookingSystem.model.seat;

public interface ReservationRepository {

  public void save(reservation reservation);
  void update(reservation reservation);
  reservation findById(String reservationId);
}

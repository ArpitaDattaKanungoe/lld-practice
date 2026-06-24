package main.DesignPractice.BookingSystem.Repo;

import main.DesignPractice.BookingSystem.model.reservation;

public interface ReservationRepository {

  public void save(reservation reservation);
  void update(reservation reservation);
  reservation findById(String reservationId);
}

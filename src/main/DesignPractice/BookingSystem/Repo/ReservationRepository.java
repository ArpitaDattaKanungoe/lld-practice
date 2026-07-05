package main.DesignPractice.BookingSystem.Repo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import main.DesignPractice.BookingSystem.model.reservation;

public class ReservationRepository {

  private final Map<String, reservation> reservations = new ConcurrentHashMap<>();

  public void save(reservation reservation) {
    reservations.put(reservation.getReservationId(), reservation);
  }

  public void update(reservation reservation) {
    reservations.put(reservation.getReservationId(), reservation);
  }

  public reservation findById(String reservationId) {
    return reservations.get(reservationId);
  }
}

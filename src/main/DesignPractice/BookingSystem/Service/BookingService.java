package main.DesignPractice.BookingSystem.Service;

import main.DesignPractice.BookingSystem.Repo.BookingRepository;
import main.DesignPractice.BookingSystem.Repo.ReservationRepository;
import main.DesignPractice.BookingSystem.Repo.SeatRepository;
import main.DesignPractice.BookingSystem.model.SeatStatus;
import main.DesignPractice.BookingSystem.model.booking;
import main.DesignPractice.BookingSystem.model.bookingStatus;
import main.DesignPractice.BookingSystem.model.payment;
import main.DesignPractice.BookingSystem.model.reservation;
import main.DesignPractice.BookingSystem.model.reservationStatus;
import main.DesignPractice.BookingSystem.model.seat;
import java.util.UUID;

public class BookingService {

  private final BookingRepository bookingRepository;
  private final ReservationRepository reservationRepository;
  private final SeatRepository showSeatRepository;

  public BookingService(
      BookingRepository bookingRepository,
      ReservationRepository reservationRepository,
      SeatRepository showSeatRepository) {

    this.bookingRepository = bookingRepository;
    this.reservationRepository = reservationRepository;
    this.showSeatRepository = showSeatRepository;
  }

  public booking confirmBooking(
      reservation reservation,
      payment payment) {

    seat seat =
        showSeatRepository.findById(reservation.getSeatId());

    // Seat : HELD -> BOOKED
    seat.setStatus(SeatStatus.BOOKED);
    showSeatRepository.update(seat);

    // Reservation : HELD -> CONFIRMED
    reservation.setStatus(
        reservationStatus.CONFIRMED);

    reservationRepository.update(reservation);

    booking booking =
        new booking(
            UUID.randomUUID().toString(),
            reservation.getReservationId(),
            payment.getPaymentId(), bookingStatus.CONFIRMED);

    return bookingRepository.save(booking);
  }
}

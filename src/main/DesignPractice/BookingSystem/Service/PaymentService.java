package main.DesignPractice.BookingSystem.Service;

import main.DesignPractice.BookingSystem.Repo.PaymentRepository;
import main.DesignPractice.BookingSystem.Repo.ReservationRepository;
import main.DesignPractice.BookingSystem.Repo.SeatRepository;
import main.DesignPractice.BookingSystem.model.SeatStatus;
import main.DesignPractice.BookingSystem.model.booking;
import main.DesignPractice.BookingSystem.model.payment;
import main.DesignPractice.BookingSystem.model.paymentStatus;
import main.DesignPractice.BookingSystem.model.reservation;
import main.DesignPractice.BookingSystem.model.reservationStatus;
import main.DesignPractice.BookingSystem.model.seat;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final ReservationRepository reservationRepository;
  private final SeatRepository showSeatRepository;
  private final BookingService bookingService;
  private final PaymentGateway paymentGateway;

  public PaymentService(
      PaymentRepository paymentRepository,
      ReservationRepository reservationRepository,
      SeatRepository showSeatRepository,
      BookingService bookingService,
      PaymentGateway paymentGateway) {

    this.paymentRepository = paymentRepository;
    this.reservationRepository = reservationRepository;
    this.showSeatRepository = showSeatRepository;
    this.bookingService = bookingService;
    this.paymentGateway = paymentGateway;
  }

  public booking makePayment(String reservationId, double amount) {

    // 1. Get Reservation
    reservation reservation =
        reservationRepository.findById(reservationId);

    if (reservation == null) {
      throw new RuntimeException("Reservation not found.");
    }

    // 2. Check reservation expiry
    if (reservation.getExpiresAt().isBefore(LocalDateTime.now())) {
      // Expired
      reservation.setStatus(reservationStatus.EXPIRED);
      reservationRepository.update(reservation);

      seat seat =
          showSeatRepository.findById(
              reservation.getSeatId());

      seat.setStatus(SeatStatus.AVAILABLE);
      showSeatRepository.update(seat);

      throw new RuntimeException("Reservation expired.");
    }

    // 3. Call Payment Gateway
    boolean paymentSuccessful = paymentGateway.pay(amount);

    // 4. Create Payment Entity
    payment payment = new payment(
        UUID.randomUUID().toString(), reservationId, amount,
        paymentSuccessful ? paymentStatus.SUCCESS : paymentStatus.FAILED);

    paymentRepository.save(payment);

    // 5. Payment Failed
    if (!paymentSuccessful) {

      reservation.setStatus(reservationStatus.CANCELLED);

      reservationRepository.update(reservation);

      seat seat = showSeatRepository.findById(reservation.getSeatId());

      seat.setStatus(SeatStatus.AVAILABLE);

      showSeatRepository.update(seat);

      throw new RuntimeException("Payment Failed.");
    }

    // 6. Payment Success
    return bookingService.confirmBooking(reservation, payment);
  }
}
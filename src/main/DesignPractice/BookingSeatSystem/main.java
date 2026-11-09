package main.DesignPractice.BookingSeatSystem;

import main.DesignPractice.BookingSeatSystem.Repo.BookingRepository;
import main.DesignPractice.BookingSeatSystem.Repo.PaymentRepository;
import main.DesignPractice.BookingSeatSystem.Repo.ReservationRepository;
import main.DesignPractice.BookingSeatSystem.Repo.SeatRepository;
import main.DesignPractice.BookingSeatSystem.Service.BookingService;
import main.DesignPractice.BookingSeatSystem.Service.LockService;
import main.DesignPractice.BookingSeatSystem.Service.PaymentGateway;
import main.DesignPractice.BookingSeatSystem.Service.PaymentService;
import main.DesignPractice.BookingSeatSystem.Service.RedisLockService;
import main.DesignPractice.BookingSeatSystem.Service.ReservationService;
import main.DesignPractice.BookingSeatSystem.model.SeatStatus;
import main.DesignPractice.BookingSeatSystem.model.booking;
import main.DesignPractice.BookingSeatSystem.model.reservation;
import main.DesignPractice.BookingSeatSystem.model.seat;

public class main {

  public static void main(String[] args) {

    // Repositories
    SeatRepository showSeatRepository = new SeatRepository();
    ReservationRepository reservationRepository = new ReservationRepository();
    PaymentRepository paymentRepository = new PaymentRepository();
    BookingRepository bookingRepository = new BookingRepository();

    showSeatRepository.save(
        new seat("SHOW_SEAT_A10", "SHOW_1", "A10", SeatStatus.AVAILABLE));

    // Lock Service
    LockService lockService = new RedisLockService();

    // Gateway
    PaymentGateway paymentGateway = new PaymentGateway();

    // Services
    BookingService bookingService = new BookingService(
        bookingRepository, reservationRepository, showSeatRepository);

    PaymentService paymentService = new PaymentService(
        paymentRepository, reservationRepository,
        showSeatRepository, bookingService, paymentGateway);

    ReservationService reservationService = new ReservationService(
        reservationRepository,
        showSeatRepository,
        lockService);

    // ----------------------------------------------------
    // User reserves a seat
    // ----------------------------------------------------

    reservation reservation =
        reservationService.reserveSeat(
            "USER_101", "SHOW_SEAT_A10");

    System.out.println("Reservation Successful");
    System.out.println("Reservation Id : "
                       + reservation.getReservationId());

    // ----------------------------------------------------
    // User pays
    // ----------------------------------------------------

    booking booking = paymentService.makePayment(
            reservation.getReservationId(), 500);

    System.out.println("Booking Successful");
    System.out.println("Booking Id : "
                       + booking.getBookingId());
  }
}

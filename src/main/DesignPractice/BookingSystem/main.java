package main.DesignPractice.BookingSystem;

import main.DesignPractice.BookingSystem.Repo.BookingRepository;
import main.DesignPractice.BookingSystem.Repo.PaymentRepository;
import main.DesignPractice.BookingSystem.Repo.ReservationRepository;
import main.DesignPractice.BookingSystem.Repo.SeatRepository;
import main.DesignPractice.BookingSystem.Service.BookingService;
import main.DesignPractice.BookingSystem.Service.LockService;
import main.DesignPractice.BookingSystem.Service.PaymentGateway;
import main.DesignPractice.BookingSystem.Service.PaymentService;
import main.DesignPractice.BookingSystem.Service.RedisLockService;
import main.DesignPractice.BookingSystem.Service.ReservationService;
import main.DesignPractice.BookingSystem.model.SeatStatus;
import main.DesignPractice.BookingSystem.model.booking;
import main.DesignPractice.BookingSystem.model.reservation;
import main.DesignPractice.BookingSystem.model.seat;

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

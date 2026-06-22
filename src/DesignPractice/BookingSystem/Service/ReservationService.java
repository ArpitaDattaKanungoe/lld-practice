package DesignPractice.BookingSystem.Service;

import DesignPractice.BookingSystem.Repo.ReservationRepository;
import DesignPractice.BookingSystem.Repo.SeatRepository;
import DesignPractice.BookingSystem.model.SeatStatus;
import DesignPractice.BookingSystem.model.reservation;
import DesignPractice.BookingSystem.model.reservationStatus;
import DesignPractice.BookingSystem.model.seat;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationService {

  private final ReservationRepository reservationRepository;

  private final SeatRepository showSeatRepository;

  private final LockService lockService;

  public ReservationService(ReservationRepository reservationRepository, SeatRepository showSeatRepository,
      LockService lockService) {
    this.reservationRepository = reservationRepository;
    this.showSeatRepository = showSeatRepository;
    this.lockService = lockService;
  }

  public reservation reserveSeat(String userId, String showSeatId) {
    String lockKey = "seat:" + showSeatId;

    if (!lockService.acquireLock(lockKey)) {
      throw new RuntimeException();
    }
    try {
      seat seat = showSeatRepository.findById(showSeatId);

      if (seat.getStatus() != SeatStatus.AVAILABLE) { // || (seat.getStatus()==SeatStatus.HELD && reservation.)
        throw new RuntimeException();
      }
      seat.setStatus(SeatStatus.HELD);
      showSeatRepository.update(seat);

      reservation reservation =
          new reservation(
              UUID.randomUUID().toString(),
              showSeatId,
              userId,
              reservationStatus.HELD,
              LocalDateTime.now()
                  .plusMinutes(5));
      reservationRepository.save(reservation);
      return reservation;
    } finally {
      lockService.releaseLock(showSeatId);
    }
  }
}



package main.DesignPractice.BookingSystem.Repo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import main.DesignPractice.BookingSystem.model.booking;

public class BookingRepository {

  private final Map<String, booking> bookings = new ConcurrentHashMap<>();

  public booking save(booking booking) {
    bookings.put(booking.getBookingId(), booking);
    return booking;
  }

  public booking findById(String bookingId) {
    return bookings.get(bookingId);
  }
}

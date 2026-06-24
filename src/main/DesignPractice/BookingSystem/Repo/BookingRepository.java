package main.DesignPractice.BookingSystem.Repo;

import main.DesignPractice.BookingSystem.model.booking;

public interface BookingRepository {

  booking save(booking booking);
}

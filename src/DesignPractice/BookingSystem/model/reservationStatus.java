package DesignPractice.BookingSystem.model;

public enum reservationStatus {

  HELD, //Customer is paying.

  CONFIRMED, //Booking succeeded.

  CANCELLED, //Payment failed.

  EXPIRED //Customer never paid.
}

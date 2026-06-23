package DesignPractice.ParkingLot.CustomExceptions;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException(String message) {
        super(message);
    }
}
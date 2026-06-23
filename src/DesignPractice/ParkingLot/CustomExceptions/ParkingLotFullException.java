package DesignPractice.ParkingLot.CustomExceptions;

public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException(String message) {
        super(message);
    }
}
package DesignPractice.ParkingLot.Payment;

public abstract class Card {
  private CardType cardType;
  private int pin;
  private double amout;

  public boolean enterPin(int pin){
    boolean checkPin = cardType.checkPin(pin);
    return checkPin;
  }
}

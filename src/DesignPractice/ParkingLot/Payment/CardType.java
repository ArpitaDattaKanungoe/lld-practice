package DesignPractice.ParkingLot.Payment;

public enum CardType {
  DEBIT(100,221),
  CREDIT(200,112);

  private final double amountLimit;
  private final int pin;

  CardType(double amountLimit, int pin) {
    this.amountLimit = amountLimit;
    this.pin = pin;
  }

  public double getAmountLimit() {
    return amountLimit;
  }
  public boolean checkPin(int pin){
    if(this.pin==pin){
      return true;
    }
    return false;
  }
}

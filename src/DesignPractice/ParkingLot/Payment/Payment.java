package DesignPractice.ParkingLot.Payment;

public class Payment {
  private MakePayment strategy;
  private Double amount;

  public Payment(MakePayment strategy, Double amount) {
    this.strategy = strategy;
    this.amount = amount;
  }
  public void paymentProcess(){
    strategy.pay(amount);
  }
}

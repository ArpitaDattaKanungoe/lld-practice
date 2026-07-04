package main.DesignPractice.FoodOrdingSystem;

public class OrderFactory {

  public Order createOrder(PaymentMethod paymentMethod) {

    if (paymentMethod == PaymentMethod.ONLINE) {
      return new OnlineOrder();
    }

    return new CODOrder();
  }
}

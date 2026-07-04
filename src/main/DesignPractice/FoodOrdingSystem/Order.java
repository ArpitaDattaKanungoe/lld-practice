package main.DesignPractice.FoodOrdingSystem;

import java.util.List;

public abstract class Order {
  private String orderId;
  private Customer customer;
  private Restaurant restaurant;
  private List<OrderItem> items;
  private double totalAmount;
  private Payment payment;
  private OrderStatus orderStatus;

  public abstract PaymentMethod getPaymentMethod();
}

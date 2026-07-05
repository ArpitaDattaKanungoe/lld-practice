package main.DesignPractice.BookingSystem.Repo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import main.DesignPractice.BookingSystem.model.payment;

public class PaymentRepository {

  private final Map<String, payment> payments = new ConcurrentHashMap<>();

  public void save(payment payment) {
    payments.put(payment.getPaymentId(), payment);
  }

  public payment findById(String paymentId) {
    return payments.get(paymentId);
  }
}

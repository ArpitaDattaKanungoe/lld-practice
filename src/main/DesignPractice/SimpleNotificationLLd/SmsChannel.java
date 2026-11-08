package main.DesignPractice.SimpleNotificationLLd;

public class SmsChannel implements NotificationChannel {
  public void send(NotificationDelivery d) {
    System.out.println("Sending SMS to user " + d.userId);
  }
}

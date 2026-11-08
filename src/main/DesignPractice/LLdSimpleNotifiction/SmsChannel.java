package main.DesignPractice.LLdSimpleNotifiction;

public class SmsChannel implements NotificationChannel {
  public void send(NotificationDelivery d) {
    System.out.println("Sending SMS to user " + d.userId);
  }
}

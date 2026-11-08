package main.DesignPractice.SimpleNotificationLLd;

public class EmailChannel implements NotificationChannel {
  public void send(NotificationDelivery d) {
    System.out.println("Sending EMAIL to user " + d.userId);
  }
}

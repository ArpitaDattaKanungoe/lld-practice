package main.DesignPractice.LLdSimpleNotifiction;

public class PushChannel implements NotificationChannel {
  public void send(NotificationDelivery d) {
    System.out.println("Sending PUSH to user " + d.userId);
  }
}

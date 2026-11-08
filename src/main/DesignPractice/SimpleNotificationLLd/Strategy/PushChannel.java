package main.DesignPractice.SimpleNotificationLLd.Strategy;

import main.DesignPractice.SimpleNotificationLLd.NotificationDelivery;

public class PushChannel implements NotificationChannel {
  public void send(NotificationDelivery d) {
    System.out.println("Sending PUSH to user " + d.getUserId());
  }
}

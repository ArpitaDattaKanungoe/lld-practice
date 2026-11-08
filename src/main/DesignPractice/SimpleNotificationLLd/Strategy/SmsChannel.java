package main.DesignPractice.SimpleNotificationLLd.Strategy;

import main.DesignPractice.SimpleNotificationLLd.NotificationDelivery;

public class SmsChannel implements NotificationChannel {
  public void send(NotificationDelivery d) {
    System.out.println("Sending SMS to user " + d.getUserId());
  }
}

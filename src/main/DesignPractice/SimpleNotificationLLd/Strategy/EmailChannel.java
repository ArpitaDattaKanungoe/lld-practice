package main.DesignPractice.SimpleNotificationLLd.Strategy;

import main.DesignPractice.SimpleNotificationLLd.NotificationDelivery;

public class EmailChannel implements NotificationChannel {
  public void send(NotificationDelivery d) {
    System.out.println("Sending EMAIL to user " + d.getUserId());
  }
}

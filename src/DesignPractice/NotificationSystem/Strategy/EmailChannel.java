package DesignPractice.NotificationSystem.Strategy;

import DesignPractice.NotificationSystem.Decorator.Notification;
import DesignPractice.NotificationSystem.NotificationContent;

public class EmailChannel implements NotificationChannel {

  @Override
  public void send(NotificationContent notification) {
    System.out.println("Sending Email to user : " + notification.getUserId() + " :" + notification.getContent());
  }
}

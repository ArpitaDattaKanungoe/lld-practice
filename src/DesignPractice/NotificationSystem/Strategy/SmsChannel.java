package DesignPractice.NotificationSystem.Strategy;

import DesignPractice.NotificationSystem.Decorator.Notification;
import DesignPractice.NotificationSystem.NotificationContent;

public class SmsChannel implements NotificationChannel {

  @Override
  public void send(NotificationContent notification) {
    System.out.println("Sending Sms to user : " + notification.getUserId() + " :" + notification.getContent());
  }
}

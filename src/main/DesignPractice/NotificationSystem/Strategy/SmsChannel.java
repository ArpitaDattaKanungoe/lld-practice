package main.DesignPractice.NotificationSystem.Strategy;

import main.DesignPractice.NotificationSystem.NotificationContent;

public class SmsChannel implements NotificationChannel {

  @Override
  public void send(NotificationContent notification) {
    System.out.println("Sending Sms to user : " + notification.getUserId() + " :" + notification.getContent());
  }
}

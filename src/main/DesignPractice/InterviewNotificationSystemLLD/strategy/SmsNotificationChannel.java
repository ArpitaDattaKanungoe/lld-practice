package main.DesignPractice.InterviewNotificationSystemLLD.strategy;

import main.DesignPractice.InterviewNotificationSystemLLD.model.Notification;

public class SmsNotificationChannel implements NotificationChannel {
  @Override
  public void send(Notification notification) {
    System.out.println(
        "Sending SMS to user " + notification.getUserId()
        + ": " + notification.getMessage()
    );
  }
}

package main.DesignPractice.InterviewNotificationSystemLLD.strategy;

import main.DesignPractice.InterviewNotificationSystemLLD.model.Notification;

public class PushNotificationChannel implements NotificationChannel {
  @Override
  public void send(Notification notification) {
    System.out.println(
        "Sending PUSH to user " + notification.getUserId()
        + ": " + notification.getMessage()
    );
  }
}

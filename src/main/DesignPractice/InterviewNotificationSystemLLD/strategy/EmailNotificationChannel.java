package main.DesignPractice.InterviewNotificationSystemLLD.strategy;

import main.DesignPractice.InterviewNotificationSystemLLD.model.Notification;
import main.DesignPractice.NotificationSystem.NotificationContent;

public class EmailNotificationChannel implements NotificationChannel {
  @Override
  public void send(Notification notification) {
    System.out.println(
        "Sending EMAIL to user " + notification.getUserId()
        + ": " + notification.getMessage()
    );
  }
}

package main.DesignPractice.InterviewNotificationSystemLLD;

import main.DesignPractice.InterviewNotificationSystemLLD.model.Notification;

public class NotificationService {
  private final NotificationDispatcher dispatcher;

  public NotificationService(NotificationDispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public void sendNotification(Notification notification) {
    dispatcher.dispatch(notification);
  }
}

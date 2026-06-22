package DesignPractice.NotificationSystem.Observer;

import DesignPractice.NotificationSystem.Decorator.Notification;
import DesignPractice.NotificationSystem.NotificationContent;
import DesignPractice.NotificationSystem.NotificationService;

public class NotificationEngine implements Observer {

  private final NotificationService service;

  public NotificationEngine(NotificationService service) {
    this.service = service;
  }

  @Override
  public void update(NotificationContent notification) {
    service.sendNotification(notification);
  }
}

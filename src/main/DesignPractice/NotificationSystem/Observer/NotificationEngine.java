package main.DesignPractice.NotificationSystem.Observer;

import main.DesignPractice.NotificationSystem.NotificationContent;
import main.DesignPractice.NotificationSystem.NotificationService;

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

package DesignPractice.NotificationSystem.Observer;

import DesignPractice.NotificationSystem.Decorator.Notification;
import DesignPractice.NotificationSystem.NotificationContent;

public class Logger implements Observer {
  @Override
  public void update(NotificationContent notification) {
    System.out.println("logging : " + notification.getContent());
  }
}

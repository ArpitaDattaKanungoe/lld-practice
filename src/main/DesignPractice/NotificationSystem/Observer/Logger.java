package main.DesignPractice.NotificationSystem.Observer;

import main.DesignPractice.NotificationSystem.NotificationContent;

public class Logger implements Observer {
  @Override
  public void update(NotificationContent notification) {
    System.out.println("logging : " + notification.getContent());
  }
}

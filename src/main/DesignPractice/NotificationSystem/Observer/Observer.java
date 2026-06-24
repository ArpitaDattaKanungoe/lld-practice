package main.DesignPractice.NotificationSystem.Observer;

import main.DesignPractice.NotificationSystem.NotificationContent;

public interface Observer {

  void update(NotificationContent notification);
}

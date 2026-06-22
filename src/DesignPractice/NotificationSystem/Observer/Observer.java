package DesignPractice.NotificationSystem.Observer;

import DesignPractice.NotificationSystem.Decorator.Notification;
import DesignPractice.NotificationSystem.NotificationContent;

public interface Observer {

  void update(NotificationContent notification);
}

package DesignPractice.NotificationSystem.Strategy;

import DesignPractice.NotificationSystem.Decorator.Notification;
import DesignPractice.NotificationSystem.NotificationContent;

public interface NotificationChannel {
 void send(NotificationContent notification);
}

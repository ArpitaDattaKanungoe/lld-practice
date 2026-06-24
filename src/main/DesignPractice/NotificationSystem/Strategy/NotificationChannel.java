package main.DesignPractice.NotificationSystem.Strategy;

import main.DesignPractice.NotificationSystem.NotificationContent;

public interface NotificationChannel {
 void send(NotificationContent notification);
}

package main.DesignPractice.SimpleNotificationLLd.Strategy;

import main.DesignPractice.SimpleNotificationLLd.NotificationDelivery;

public interface NotificationChannel {
  void send(NotificationDelivery delivery) throws Exception;
}

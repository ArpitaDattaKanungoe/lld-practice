package main.DesignPractice.SimpleNotificationLLd;

public interface NotificationChannel {
  void send(NotificationDelivery delivery) throws Exception;
}

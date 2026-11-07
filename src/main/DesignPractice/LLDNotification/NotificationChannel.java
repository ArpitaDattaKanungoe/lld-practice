package main.DesignPractice.LLDNotification;

public interface NotificationChannel {
  void send(Notification notification, NotificationDelivery delivery) throws Exception;

  String getChannelType();
}

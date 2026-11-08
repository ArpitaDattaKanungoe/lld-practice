package main.DesignPractice.LLdSimpleNotifiction;

public interface NotificationChannel {
  void send(NotificationDelivery delivery) throws Exception;
}

package main.DesignPractice.LLdSimpleNotifiction;

public class NotificationDelivery {
  String notificationId;
  int userId;
  String message;
  ChannelType channelType;
  DeliveryStatus status = DeliveryStatus.PENDING;

  NotificationDelivery(String id, int userId, String message, ChannelType channelType) {
    this.notificationId = id;
    this.userId = userId;
    this.message = message;
    this.channelType = channelType;
  }
}

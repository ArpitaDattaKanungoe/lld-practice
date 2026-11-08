package main.DesignPractice.SimpleNotificationLLd;

public class NotificationDelivery {
  String notificationId;

  public int getUserId() {
    return userId;
  }

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

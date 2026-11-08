package main.DesignPractice.SimpleNotificationLLd;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class NotificationService {
  private final PreferenceService preferenceService;
  private final BlockingQueue<NotificationDelivery> queue;

  NotificationService(PreferenceService preferenceService,
      BlockingQueue<NotificationDelivery> queue) {
    this.preferenceService = preferenceService;
    this.queue = queue;
  }

  public void sendNotification(int userId, String message) {
    String notificationId = UUID.randomUUID().toString();
    Set<ChannelType> channels = preferenceService.getEnabledChannels(userId);

    for (ChannelType channel : channels) {
      NotificationDelivery delivery = new NotificationDelivery(notificationId, userId, message, channel);
      queue.offer(delivery);
    }
  }
}

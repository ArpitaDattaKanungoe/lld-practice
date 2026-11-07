package main.DesignPractice.LLDNotification;

import java.util.Optional;

public class NotificationWorker {
  private final NotificationRepository repository;
  private final NotificationChannelFactory factory;
  private final RetryManager retryManager;

  public NotificationWorker(NotificationRepository repository, NotificationChannelFactory factory,
      RetryManager retryManager) {
    this.repository = repository;
    this.factory = factory;
    this.retryManager = retryManager;
  }

  public void process(String deliveryId) {
    Optional<NotificationDelivery> claimed = repository.claimDelivery(deliveryId);
    if (claimed.isEmpty())
      return; // Duplicate queue message or already processed.
    NotificationDelivery d = claimed.get();
    try {
      Notification n = repository.getNotification(d.getNotificationId())
          .orElseThrow(() -> new IllegalStateException("Notification not found"));
      NotificationChannel channel = factory.getChannel(d.getChannelType().name());
      channel.send(n, d);
      d.setStatus(DeliveryStatus.SENT);
      d.setFailureReason(null);
      d.setNextRetryAt(null);
      retryManager.updateParentStatus(d.getNotificationId());
    } catch (Exception e) {
      retryManager.handleFailure(d, e);
    }
  }
}

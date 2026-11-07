package main.DesignPractice.LLDNotification;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RetryManager {

  private final NotificationRepository repository;
  private final NotificationQueue queue;
  private final ScheduledExecutorService scheduler;
  private final int maxRetries;

  public RetryManager(NotificationRepository repository, NotificationQueue queue,
      ScheduledExecutorService scheduler, int maxRetries) {
    this.repository = repository;
    this.queue = queue;
    this.scheduler = scheduler;
    this.maxRetries = maxRetries;
  }

  public void handleFailure(NotificationDelivery d, Exception error) {
    d.incrementRetryCount();
    d.setFailureReason(error.getMessage());
    if (d.getRetryCount() > maxRetries) {
      d.setStatus(DeliveryStatus.FAILED);
      d.setNextRetryAt(null);
      updateParentStatus(d.getNotificationId());
      System.err.println("[DLQ] Delivery exhausted retries: " + d.getDeliveryId());
      return;
    }
    long delaySeconds = 1L << (d.getRetryCount() - 1); // 1, 2, 4 seconds...
    d.setStatus(DeliveryStatus.RETRY_SCHEDULED);
    d.setNextRetryAt(Instant.now().plusSeconds(delaySeconds));
    scheduler.schedule(() -> {
      try {
        queue.publish(d.getDeliveryId());
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }, delaySeconds, TimeUnit.SECONDS);
    updateParentStatus(d.getNotificationId());
  }

  public void updateParentStatus(String notificationId) {
    List<NotificationDelivery> list = repository.getDeliveries(notificationId);
    if (list.isEmpty()) {
      return;
    }
    boolean allSent = list.stream().allMatch(d -> d.getStatus() == DeliveryStatus.SENT);
    boolean active = list.stream()
        .anyMatch(d -> d.getStatus() == DeliveryStatus.PENDING
            || d.getStatus() == DeliveryStatus.PROCESSING
            || d.getStatus() == DeliveryStatus.RETRY_SCHEDULED);
    boolean anySent = list.stream().anyMatch(d -> d.getStatus() == DeliveryStatus.SENT);
    NotificationStatus status = allSent ? NotificationStatus.COMPLETED
        : active ? NotificationStatus.PROCESSING
            : anySent ? NotificationStatus.PARTIALLY_FAILED : NotificationStatus.FAILED;
    repository.updateNotificationStatus(notificationId, status);
  }
}

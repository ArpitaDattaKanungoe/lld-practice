package main.DesignPractice.LLDNotification;

import java.time.Instant;

public class PendingDeliveryRecoveryJob implements Runnable {
  private final NotificationRepository repository;
  private final NotificationQueue queue;

  public PendingDeliveryRecoveryJob(NotificationRepository repository, NotificationQueue queue) {
    this.repository = repository;
    this.queue = queue;
  }

  public void run() {
    for (NotificationDelivery d : repository.findRecoverableDeliveries(Instant.now())) {
      try {
        queue.publish(d.getDeliveryId());
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      }
    }
  }
}

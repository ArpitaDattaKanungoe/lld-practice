package main.DesignPractice.LLDNotification;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class NotificationApplication {
  public static void main(String[] args) throws Exception {
    NotificationRepository repository = new InMemoryNotificationRepository();
    PreferenceService preferences = new PreferenceService();
    preferences
        .savePreference(new UserPreference(101, Set.of(ChannelType.EMAIL, ChannelType.PUSH)));

    NotificationQueue queue = new NotificationQueue();
    NotificationChannelFactory factory = new NotificationChannelFactory(
        List.of(new EmailChannel(), new SmsChannel(), new PushChannel()));
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    RetryManager retryManager = new RetryManager(repository, queue, scheduler, 3);
    NotificationWorker worker = new NotificationWorker(repository, factory, retryManager);

    ExecutorService workers = Executors.newFixedThreadPool(3);
    for (int i = 1; i <= 3; i++) {
      String workerName = "worker-" + i;
      workers.submit(() -> {
        while (!Thread.currentThread().isInterrupted()) {
          try {
            String deliveryId = queue.consume();
            System.out.println(workerName + " picked up " + deliveryId);
            worker.process(deliveryId);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      });
    }

    PendingDeliveryRecoveryJob recovery = new PendingDeliveryRecoveryJob(repository, queue);
    ScheduledFuture<?> recoveryTask = scheduler.scheduleWithFixedDelay(recovery, 5, 5,
        TimeUnit.SECONDS);

    NotificationService service = new NotificationService(preferences, repository, queue);
    Notification notification = service
        .sendNotification(new NotificationRequest(101, "Order confirmed",
            "Your order #5001 has been confirmed.", "ORDER_CONFIRMED", "ORDER_5001_CONFIRMED"));

    System.out.println("Accepted notification: " + notification.getNotificationId());
    for (int i = 0; i < 50; i++) {
      NotificationStatus status = repository.getNotification(notification.getNotificationId())
          .orElseThrow().getStatus();
      if (status == NotificationStatus.COMPLETED || status == NotificationStatus.PARTIALLY_FAILED
          || status == NotificationStatus.FAILED)
        break;
      Thread.sleep(200);
    }

    System.out.println("Final status: "
        + repository.getNotification(notification.getNotificationId()).orElseThrow().getStatus());
    repository.getDeliveries(notification.getNotificationId()).forEach(System.out::println);

    recoveryTask.cancel(true);
    workers.shutdownNow();
    scheduler.shutdownNow();
    workers.awaitTermination(2, TimeUnit.SECONDS);
    scheduler.awaitTermination(2, TimeUnit.SECONDS);
  }
}

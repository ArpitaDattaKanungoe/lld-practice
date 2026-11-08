package main.DesignPractice.SimpleNotificationLLd;

import java.util.concurrent.BlockingQueue;

class NotificationWorker {
  private final BlockingQueue<NotificationDelivery> queue;
  private final NotificationChannelFactory factory;
  private static final int MAX_ATTEMPTS = 3;

  NotificationWorker(BlockingQueue<NotificationDelivery> queue,
      NotificationChannelFactory factory) {
    this.queue = queue;
    this.factory = factory;
  }

  public void processNotifications() {
    while (!Thread.currentThread().isInterrupted()) {
      try {
        NotificationDelivery delivery = queue.take(); // Waits until an item is available
        process(delivery);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      }
    }
  }

  private void process(NotificationDelivery delivery) {
    NotificationChannel channel =
        factory.getChannel(delivery.channelType);

    for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
      try {
        channel.send(delivery);
        delivery.status = DeliveryStatus.SENT;
        System.out.println("Delivery successful");
        return;
      } catch (Exception e) {
        System.out.println("Attempt " + attempt + " failed");
      }
    }

    delivery.status = DeliveryStatus.FAILED;
    System.out.println("Delivery failed after retries");
  }
}



/*
  public class NotificationWorker implements Runnable {
  private final BlockingQueue<NotificationDelivery> queue;
  private final NotificationChannelFactory factory;
  private static final int MAX_ATTEMPTS = 3;

  NotificationWorker(BlockingQueue<NotificationDelivery> queue,
      NotificationChannelFactory factory) {
    this.queue = queue;
    this.factory = factory;
  }

  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        NotificationDelivery delivery = queue.take();
        process(delivery);
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void process(NotificationDelivery delivery) {
    NotificationChannel channel = factory.getChannel(delivery.channelType);

    for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
      try {
        channel.send(delivery);
        delivery.status = DeliveryStatus.SENT;
        System.out.println("Delivery successful");
        return;
      } catch (Exception e) {
        System.out.println("Attempt " + attempt + " failed");
      }
    }

    delivery.status = DeliveryStatus.FAILED;
    System.out.println("Delivery failed after retries");
  }
}
 */



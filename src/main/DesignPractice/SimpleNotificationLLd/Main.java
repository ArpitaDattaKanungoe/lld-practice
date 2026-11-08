package main.DesignPractice.SimpleNotificationLLd;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

  public static void main(String[] args) throws Exception {

    BlockingQueue<NotificationDelivery> queue = new LinkedBlockingQueue<>(); // LinkedBlockingQueue for Thread safety
    PreferenceService preferenceService = new PreferenceService();
    preferenceService.setPreferences(101, Set.of(ChannelType.EMAIL, ChannelType.SMS));
    NotificationChannelFactory factory = new NotificationChannelFactory();
    NotificationService service = new NotificationService(preferenceService, queue);
    NotificationWorker worker = new NotificationWorker(queue, factory);

    // Start worker in a separate thread
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(() -> worker.processNotifications());

    // Submit notification
    service.sendNotification(101, "Your order has been shipped");

    // Allow the asynchronous deliveries to finish before stopping this demo.
    try {
      Thread.sleep(1000);
    } finally {
      executor.shutdownNow();
    }
  }

  //  public static void main(String[] args) throws Exception {
//    BlockingQueue<NotificationDelivery> queue = new LinkedBlockingQueue<>();
//    PreferenceService preferenceService = new PreferenceService();
//    preferenceService.setPreferences(101, Set.of(ChannelType.EMAIL, ChannelType.SMS));
//
//    NotificationChannelFactory factory = new NotificationChannelFactory();
//    NotificationService service = new NotificationService(preferenceService, queue);
//    ExecutorService executor = Executors.newSingleThreadExecutor();
//    executor.submit(new NotificationWorker(queue, factory));
//
//    // Submit notification. Actual sending happens asynchronously.
//    service.sendNotification(101, "Your order has been shipped");
//
//    Thread.sleep(1000);
//    executor.shutdownNow();
//  }


}



package main.DesignPractice.LLDNotification;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NotificationQueue {
  private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

  public void publish(String deliveryId) throws InterruptedException {
    queue.put(deliveryId);
  }

  public String consume() throws InterruptedException {
    return queue.take();
  }
}

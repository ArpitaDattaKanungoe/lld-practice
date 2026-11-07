package main.DesignPractice.LLDNotification;

public class PushChannel implements NotificationChannel {
  public void send(Notification n, NotificationDelivery d) {
    System.out
        .println("[PUSH] user=" + n.getUserId() + " | " + n.getSubject() + " | " + n.getMessage());
  }

  public String getChannelType() {
    return ChannelType.PUSH.name();
  }
}

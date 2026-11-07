package main.DesignPractice.LLDNotification;

public class SmsChannel implements NotificationChannel {
  public void send(Notification n, NotificationDelivery d) {
    System.out.println("[SMS] user=" + n.getUserId() + " | " + n.getMessage());
  }

  public String getChannelType() {
    return ChannelType.SMS.name();
  }
}

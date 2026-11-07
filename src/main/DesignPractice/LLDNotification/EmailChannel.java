package main.DesignPractice.LLDNotification;

public class EmailChannel implements NotificationChannel {
  public void send(Notification n, NotificationDelivery d) {
    System.out.println("[EMAIL] user=" + n.getUserId() + " | " + n.getSubject() + " | " + n.getMessage());
  }

  public String getChannelType() {
    return ChannelType.EMAIL.name();
  }
}

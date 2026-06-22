package DesignPractice.NotificationSystem;

public class NotificationService {

  NotificationDispatcher dispatcher;

  public NotificationService(NotificationDispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  public void sendNotification(NotificationContent notification) {
    dispatcher.dispatch(notification);
  }
}

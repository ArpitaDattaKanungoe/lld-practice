package main.DesignPractice.InterviewNotificationSystemLLD;

import java.util.Set;
import main.DesignPractice.InterviewNotificationSystemLLD.model.ChannelType;
import main.DesignPractice.InterviewNotificationSystemLLD.model.Notification;
import main.DesignPractice.InterviewNotificationSystemLLD.model.UserPreference;

public class Client {
  public static void main(String[] args) {

    // Defining preference service.
    UserPreferenceService preferenceService = new UserPreferenceService();

    // Defining user preference with Email and SMS as preferred channels.
    preferenceService.savePreference(
        new UserPreference(
            "user123", Set.of(ChannelType.EMAIL, ChannelType.SMS))
    );

    // Defining notification dispatcher
    NotificationDispatcher dispatcher =
        new NotificationDispatcher(preferenceService);

    // Defining synchronous service.
    NotificationService service = new NotificationService(dispatcher);

    // Defining notification to send through multiple channels.
    Notification notification =
        new Notification("user123", "Your order has been shipped!");

    // Sending notification through synchronous service.
    service.sendNotification(notification);
  }
}

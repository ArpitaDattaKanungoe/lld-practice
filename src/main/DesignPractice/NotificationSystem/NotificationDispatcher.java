package main.DesignPractice.NotificationSystem;

import main.DesignPractice.NotificationSystem.Factory.ChannelType;
import main.DesignPractice.NotificationSystem.Factory.NotificationChannelFactory;
import main.DesignPractice.NotificationSystem.Strategy.NotificationChannel;
import java.util.Set;

public class NotificationDispatcher {

  private UserPreferenceService preferenceService;

  public NotificationDispatcher(UserPreferenceService preferenceService) {
    this.preferenceService = preferenceService;
  }

  public void dispatch(NotificationContent notification) {
    UserPreferences preference = preferenceService.getPreference(notification.getUserId());
    if (preference != null) {
      Set<ChannelType> preferredChannel = preference.getPreferredChannel();
      for (ChannelType c : preferredChannel) {
        NotificationChannel channel = NotificationChannelFactory.getChannel(c);
        channel.send(notification);
      }
    } else {
      NotificationChannel emailChannel = NotificationChannelFactory.getChannel(ChannelType.Email);
      emailChannel.send(notification);
      System.out.println(
          "User preferences not found for user " + notification.getUserId() + ". Sent via Email by default.");
    }
  }
}

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
      Set<ChannelType> preferredChannel = preference.getPreferredChannel();
      for (ChannelType c : preferredChannel) {
        NotificationChannel channel = NotificationChannelFactory.getChannel(c);
        channel.send(notification);
      }
  }
}

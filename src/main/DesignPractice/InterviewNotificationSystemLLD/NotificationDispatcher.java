package main.DesignPractice.InterviewNotificationSystemLLD;

import java.util.Set;
import main.DesignPractice.InterviewNotificationSystemLLD.factory.NotificationChannelFactory;
import main.DesignPractice.InterviewNotificationSystemLLD.model.ChannelType;
import main.DesignPractice.InterviewNotificationSystemLLD.model.Notification;
import main.DesignPractice.InterviewNotificationSystemLLD.model.UserPreference;
import main.DesignPractice.InterviewNotificationSystemLLD.strategy.NotificationChannel;

public class NotificationDispatcher {
  private final UserPreferenceService preferenceService;

  public NotificationDispatcher(UserPreferenceService preferenceService) {
    this.preferenceService = preferenceService;
  }

  public void dispatch(Notification notification) {
    UserPreference preference =
        preferenceService.getPreference(notification.getUserId());

    Set<ChannelType> channels = preference.getPreferredChannels();

    for (ChannelType channelType : channels) {
      NotificationChannel channel =
          NotificationChannelFactory.getChannel(channelType);

      channel.send(notification);
    }
  }
}

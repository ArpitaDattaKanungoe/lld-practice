package main.DesignPractice.SimpleNotificationLLd;

import java.util.EnumMap;
import java.util.Map;
import main.DesignPractice.SimpleNotificationLLd.Strategy.EmailChannel;
import main.DesignPractice.SimpleNotificationLLd.Strategy.NotificationChannel;
import main.DesignPractice.SimpleNotificationLLd.Strategy.PushChannel;
import main.DesignPractice.SimpleNotificationLLd.Strategy.SmsChannel;

public class NotificationChannelFactory {
  private final Map<ChannelType, NotificationChannel> channels = new EnumMap<>(ChannelType.class);

  NotificationChannelFactory() {
    channels.put(ChannelType.EMAIL, new EmailChannel());
    channels.put(ChannelType.SMS, new SmsChannel());
    channels.put(ChannelType.PUSH, new PushChannel());
  }

  public NotificationChannel getChannel(ChannelType type) {
    return channels.get(type);
  }
}

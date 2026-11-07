package main.DesignPractice.LLDNotification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationChannelFactory {
  private final Map<String, NotificationChannel> channels;

  public NotificationChannelFactory(List<NotificationChannel> channelList) {
    Map<String, NotificationChannel> map = new HashMap<>();
    for (NotificationChannel c : channelList) {
      if (map.put(c.getChannelType(), c) != null)
        throw new IllegalArgumentException("Duplicate channel: " + c.getChannelType());
    }
    channels = Map.copyOf(map);
  }

  public NotificationChannel getChannel(String type) {
    NotificationChannel c = channels.get(type);
    if (c == null)
      throw new IllegalArgumentException("Unsupported channel: " + type);
    return c;
  }
}

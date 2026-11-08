package main.DesignPractice.SimpleNotificationLLd;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PreferenceService {
  private final Map<Integer, Set<ChannelType>> preferences = new HashMap<>();

  public void setPreferences(int userId, Set<ChannelType> channels) {
    preferences.put(userId, channels);
  }

  public Set<ChannelType> getEnabledChannels(int userId) {
    return preferences.getOrDefault(userId, Set.of());
  }
}

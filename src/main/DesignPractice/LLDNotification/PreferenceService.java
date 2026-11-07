package main.DesignPractice.LLDNotification;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PreferenceService {
  private final Map<Integer, UserPreference> preferences = new ConcurrentHashMap<>();

  public void savePreference(UserPreference preference) {
    preferences.put(preference.getUserId(), preference);
  }

  public Set<ChannelType> getEnabledChannels(int userId) {
    UserPreference p = preferences.get(userId);
    return p == null ? Set.of() : p.getEnabledChannels();
  }
}

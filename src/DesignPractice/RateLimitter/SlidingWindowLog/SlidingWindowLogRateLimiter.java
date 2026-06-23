package DesignPractice.RateLimitter.SlidingWindowLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// max 3 request in 5 mints
public class SlidingWindowLogRateLimiter {

  private static final SlidingWindowLogRateLimiter INSTANCE = new SlidingWindowLogRateLimiter();

  private SlidingWindowLogRateLimiter() {
  }

  public static SlidingWindowLogRateLimiter getInstance() {
    return INSTANCE;
  }

  private static final int LIMIT = 3;
  private static final long WINDOW_SECONDS = 5 * 60L;
  Map<String, Window> windows = new ConcurrentHashMap<>();

  public boolean canAllow(String userId, long currentTime) {
    Window logs = windows.computeIfAbsent(userId, id -> new Window());

    synchronized (logs) {
      // Remove expired timestamps
      while (!logs.getTimestamps().isEmpty() && logs.getTimestamps().peekFirst() <= currentTime - WINDOW_SECONDS) {
        logs.getTimestamps().pollFirst();
      }

      // Record this request attempt
      if (logs.getTimestamps().size() < LIMIT) {
        logs.getTimestamps().offerLast(currentTime);
        return true;
      }
      return false;
    }

  }
}

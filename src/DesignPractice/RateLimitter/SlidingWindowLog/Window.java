package DesignPractice.RateLimitter.SlidingWindowLog;

import java.util.ArrayDeque;
import java.util.Deque;

public class Window {
  private final Deque<Long> timestamps = new ArrayDeque<>();

  public Deque<Long> getTimestamps() {
    return timestamps;
  }
}

package main.DesignPractice.BookingSystem.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedisLockService implements LockService {

  private final Map<String, Boolean> locks = new ConcurrentHashMap<>();

  @Override
  public boolean acquireLock(String key) {

    return locks.putIfAbsent(key, Boolean.TRUE) == null; // key not present, it inserts it and returns null.
  }

  @Override
  public void releaseLock(String key) {

    locks.remove(key);
  }
}

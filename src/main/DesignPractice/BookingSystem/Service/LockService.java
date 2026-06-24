package main.DesignPractice.BookingSystem.Service;

public interface LockService {

  boolean acquireLock(String key);

  void releaseLock(String key);
}

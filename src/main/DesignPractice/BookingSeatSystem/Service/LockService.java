package main.DesignPractice.BookingSeatSystem.Service;

public interface LockService {

  boolean acquireLock(String key);

  void releaseLock(String key);
}

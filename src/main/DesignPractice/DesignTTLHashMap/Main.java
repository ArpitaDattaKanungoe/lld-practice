package main.DesignPractice.DesignTTLHashMap;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class TTLMap<K, V> {

  private static class Entry<K, V> {
    private final K key;
    private final V value;
    private final long expiryTimeMillis;

    private Entry(K key, V value, long expiryTimeMillis) {
      this.key = key;
      this.value = value;
      this.expiryTimeMillis = expiryTimeMillis;
    }
  }
  private final Map<K, Entry<K, V>> map = new HashMap<>();

  private final PriorityQueue<Entry<K, V>> expiryQueue =
      new PriorityQueue<>((e1, e2) -> Long.compare(e1.expiryTimeMillis, e2.expiryTimeMillis));

  public synchronized void put(K key, V value, Duration ttl) {
    if (key == null || value == null || ttl == null) {
      throw new IllegalArgumentException("Key, value, and ttl are required");
    }

    if (ttl.isZero() || ttl.isNegative()) {
      throw new IllegalArgumentException("TTL must be positive");
    }

    cleanupExpiredEntries();

    long expiryTimeMillis = System.currentTimeMillis() + ttl.toMillis();

    Entry<K, V> newEntry = new Entry<>(key, value, expiryTimeMillis);

    map.put(key, newEntry);
    expiryQueue.offer(newEntry);
  }

  public synchronized V get(K key) {
    cleanupExpiredEntries();

    Entry<K, V> entry = map.get(key);

    if (entry == null) {
      return null;
    }

    return entry.value;
  }

  public synchronized int size() {
    cleanupExpiredEntries();
    return map.size();
  }

  private void cleanupExpiredEntries() {
    long now = System.currentTimeMillis();

    while (!expiryQueue.isEmpty()) {
      Entry<K, V> entry = expiryQueue.peek();

      if (entry.expiryTimeMillis > now) {
        break;
      }

      expiryQueue.poll();

      Entry<K, V> currentEntry = map.get(entry.key);

      if (currentEntry == entry) {
        map.remove(entry.key);
      }
    }
  }
}

public class Main {
  public static void main(String[] args) throws InterruptedException {
    TTLMap<String, String> cache = new TTLMap<>();

    cache.put("user1", "Arpita", Duration.ofSeconds(2));

    System.out.println(cache.get("user1")); // Arpita

    Thread.sleep(3000);

    System.out.println(cache.get("user1")); // null
    System.out.println(cache.size());       // 0
  }
}

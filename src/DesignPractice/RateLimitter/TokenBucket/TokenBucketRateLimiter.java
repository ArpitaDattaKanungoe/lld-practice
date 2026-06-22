package DesignPractice.RateLimitter.TokenBucket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {

  Map<String, Bucket> bucketsPerUser = new ConcurrentHashMap<>();
  private static final int CAPACITY = 10;
  private static final int REFILL_RATE = 2; // 2 tokens/sec

  public boolean allow(String userId) {
    Bucket bucket = bucketsPerUser.computeIfAbsent(userId, id -> new Bucket(CAPACITY, REFILL_RATE));
    synchronized (bucket){ // per user bucket object locking
      refill(bucket);
      if (bucket.getTokens() <= 0) {
        return false;
      } else {
        bucket.setTokens(bucket.getTokens() - 1);
        return true;
      }
    }
  }


  /**
   * If I had been adding tokens continuously since the last request, how many tokens would the bucket contain right now?
   *
   * Instead of actually adding tokens every second, it calculates how many would have been added based on the elapsed time,
   * updates the bucket once, and then proceeds to check and consume a token.
   */
  private void refill(Bucket bucket) { // tokens are recalculated only when a request arrives
    long currentTime = System.currentTimeMillis() / 1000;
    long previousFillingTime = bucket.getLastRefillTime();
    long diff = currentTime - previousFillingTime;
    if (diff <= 0) {
      return;
    }
    long totalTokenForDiff = diff * REFILL_RATE;
    int totalToken = (int) Math.min(bucket.getTokens() + totalTokenForDiff, bucket.getCapacity());
    bucket.setLastRefillTime(currentTime);
    bucket.setTokens(totalToken);
  }
}


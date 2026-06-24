package main.DesignPractice.RateLimitter.TokenBucket;

public class Bucket {

  private int tokens;

  private long lastRefillTime;

  private final int capacity;

  // tokens added per second
  private final int refillRate;

  public Bucket(int capacity, int refillRate) {
    this.tokens = capacity;
    this.lastRefillTime = System.currentTimeMillis()/1000;
    this.capacity = capacity;
    this.refillRate = refillRate;
  }

  public int getTokens() {
    return tokens;
  }

  public long getLastRefillTime() {
    return lastRefillTime;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getRefillRate() {
    return refillRate;
  }

  public void setTokens(int tokens) {
    this.tokens = tokens;
  }

  public void setLastRefillTime(long lastRefillTime) {
    this.lastRefillTime = lastRefillTime;
  }
}

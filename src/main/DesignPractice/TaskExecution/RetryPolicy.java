package main.DesignPractice.TaskExecution;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

class RetryPolicy {

  private final int maxAttempts;

  private final Duration initialDelay;

  private final double multiplier;

  private final Duration maxDelay;

  private final boolean jitterEnabled;

  public RetryPolicy(
      int maxAttempts,
      Duration initialDelay,
      double multiplier,
      Duration maxDelay,
      boolean jitterEnabled) {

    if (maxAttempts < 1 ||
        initialDelay.isNegative() ||
        multiplier < 1 ||
        maxDelay.isNegative()) {

      throw new IllegalArgumentException(
          "Invalid retry configuration"
      );
    }

    this.maxAttempts = maxAttempts;
    this.initialDelay = initialDelay;
    this.multiplier = multiplier;
    this.maxDelay = maxDelay;
    this.jitterEnabled = jitterEnabled;
  }

  public Duration calculateDelay(int retryNumber) {

    double delay =
        initialDelay.toMillis()
        * Math.pow(multiplier, retryNumber - 1);

    long cappedDelay = Math.min(
        (long) delay,
        maxDelay.toMillis()
    );

    if (jitterEnabled && cappedDelay > 0) {

      cappedDelay = ThreadLocalRandom.current()
          .nextLong(cappedDelay + 1);
    }

    return Duration.ofMillis(cappedDelay);
  }

  public int getMaxAttempts() {
    return maxAttempts;
  }
}
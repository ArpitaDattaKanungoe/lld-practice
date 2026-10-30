package main.DesignPractice.TaskExecution;

import java.time.Duration;

public class RetryPolicy {

  private int maxAttempts;

  private Duration initialDelay;

  private double multiplier;

  private Duration maxDelay;

  private boolean jitterEnabled;
}

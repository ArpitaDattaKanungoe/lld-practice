package main.DesignPractice.TaskExecution;

import java.time.Instant;

class Task {
  private String taskId;
  private Runnable action;

  private TaskStatus status;

  private int currentAttempt; // We need this field to determine whether another retry is allowed.
  private int maxAttempts;

  private Instant nextExecutionTime;

  private String lastError;

  private RetryPolicy retryPolicy;
}

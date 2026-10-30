package main.DesignPractice.TaskExecution;

import java.time.Instant;

import java.time.Instant;

class Task {

  private final String taskId;

  private final Runnable action;

  private final RetryPolicy retryPolicy;

  private TaskStatus status;

  private int currentAttempt;

  private Instant nextExecutionTime;

  private String lastError;

  private volatile boolean cancellationRequested;

  public Task(
      String taskId,
      Runnable action,
      RetryPolicy retryPolicy) {

    this.taskId = taskId;
    this.action = action;
    this.retryPolicy = retryPolicy;

    this.status = TaskStatus.SUBMITTED;
    this.currentAttempt = 0;
  }

  public synchronized boolean startAttempt() {

    if (cancellationRequested ||
        status == TaskStatus.CANCELLED ||
        status == TaskStatus.COMPLETED ||
        status == TaskStatus.EXHAUSTED) {

      return false;
    }

    status = TaskStatus.RUNNING;
    currentAttempt++;

    return true;
  }

  public synchronized void markCompleted() {

    if (cancellationRequested) {
      status = TaskStatus.CANCELLED;
    } else {
      status = TaskStatus.COMPLETED;
    }
  }

  public synchronized void markRetryScheduled(
      Instant nextExecutionTime) {

    if (cancellationRequested) {
      status = TaskStatus.CANCELLED;
      return;
    }

    this.status = TaskStatus.RETRY_SCHEDULED;
    this.nextExecutionTime = nextExecutionTime;
  }

  public synchronized void markExhausted(String error) {

    this.lastError = error;
    this.status = TaskStatus.EXHAUSTED;
  }

  public synchronized void markFailure(String error) {
    this.lastError = error;
  }

  public synchronized void requestCancellation() {

    cancellationRequested = true;

    if (status != TaskStatus.RUNNING) {
      status = TaskStatus.CANCELLED;
    } else {
      status = TaskStatus.CANCELLATION_REQUESTED;
    }
  }

  public boolean isCancellationRequested() {
    return cancellationRequested;
  }

  public String getTaskId() {
    return taskId;
  }

  public Runnable getAction() {
    return action;
  }

  public RetryPolicy getRetryPolicy() {
    return retryPolicy;
  }

  public synchronized TaskStatus getStatus() {
    return status;
  }

  public synchronized int getCurrentAttempt() {
    return currentAttempt;
  }

  public synchronized Instant getNextExecutionTime() {
    return nextExecutionTime;
  }

  public synchronized String getLastError() {
    return lastError;
  }
}

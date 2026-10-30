package main.DesignPractice.TaskExecution;

import java.time.Duration;
import java.time.Instant;

class RetryManager {

  private final TaskRepository repository;

  private final TaskScheduler scheduler;

  private final FailureHandler failureHandler;

  public RetryManager(
      TaskRepository repository,
      TaskScheduler scheduler,
      FailureHandler failureHandler) {

    this.repository = repository;
    this.scheduler = scheduler;
    this.failureHandler = failureHandler;
  }

  public boolean shouldRetry(Task task) {

    return task.getCurrentAttempt() < task.getRetryPolicy().getMaxAttempts();
  }

  public void handleFailure(Task task, Exception error) {

    if (task.isCancellationRequested()) {
      task.requestCancellation();
      repository.update(task);
      return;
    }

    task.markFailure(error.getMessage());

    if (shouldRetry(task)) {

      int retryNumber = task.getCurrentAttempt();

      Duration delay = task.getRetryPolicy()
          .calculateDelay(retryNumber);

      Instant nextExecutionTime =
          Instant.now().plus(delay);

      task.markRetryScheduled(nextExecutionTime);

      repository.update(task);

      System.out.println(
          "Retry scheduled for task: " +
          task.getTaskId() +
          " after " + delay.toMillis() + " ms"
      );

      scheduler.schedule(task, delay);

    } else {

      task.markExhausted(error.getMessage());

      repository.update(task);

      failureHandler.handle(task, error);
    }
  }
}

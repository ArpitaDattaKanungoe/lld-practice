package main.DesignPractice.TaskExecution;

import java.time.Duration;

class TaskManager {

  private final TaskRepository repository;

  private final TaskScheduler scheduler;

  private final TaskExecutor executor;

  private final RetryManager retryManager;

  public TaskManager(int workerPoolSize) {

    this.repository = new TaskRepository();

    this.scheduler = new TaskScheduler();

    FailureHandler failureHandler =
        new DefaultFailureHandler();

    this.retryManager = new RetryManager(
        repository, scheduler, failureHandler
    );

    this.executor = new TaskExecutor(
        repository, retryManager, workerPoolSize
    );

    // Complete the scheduler-executor wiring.
    this.scheduler.setTaskExecutor(executor);
  }

  // Submit a new task
  public String submit(Task task) {

    if (repository.findById(task.getTaskId()) != null) {
      throw new IllegalArgumentException(
          "Task already exists: " + task.getTaskId()
      );
    }

    repository.save(task);

    scheduler.schedule(task, Duration.ZERO);

    return task.getTaskId();
  }

  // Get task status
  public TaskStatus getStatus(String taskId) {

    Task task = repository.findById(taskId);

    if (task == null) {
      throw new IllegalArgumentException(
          "Task not found: " + taskId
      );
    }

    return task.getStatus();
  }

  // Cancel task
  public boolean cancel(String taskId) {

    Task task = repository.findById(taskId);

    if (task == null) {
      return false;
    }

    TaskStatus status = task.getStatus();

    if (status == TaskStatus.COMPLETED ||
        status == TaskStatus.EXHAUSTED ||
        status == TaskStatus.CANCELLED) {

      return false;
    }

    task.requestCancellation();

    // Cancel future scheduled execution.
    scheduler.cancel(taskId);

    // Interrupt running execution if possible.
    executor.cancel(taskId);

    repository.update(task);

    return true;
  }

  public void shutdown() {

    scheduler.shutdown();
    executor.shutdown();
  }
}

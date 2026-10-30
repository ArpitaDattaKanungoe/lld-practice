package main.DesignPractice.TaskExecution;

import java.util.concurrent.*;

class TaskExecutor {

  private final ExecutorService workerPool;

  private final TaskRepository repository;

  private final RetryManager retryManager;

  private final ConcurrentHashMap<String, Future<?>> runningTasks =
      new ConcurrentHashMap<>();

  public TaskExecutor(
      TaskRepository repository,
      RetryManager retryManager,
      int poolSize) {

    this.repository = repository;
    this.retryManager = retryManager;

    this.workerPool = Executors.newFixedThreadPool(poolSize);
  }

  public void execute(Task task) {

    FutureTask<Void> future = new FutureTask<>(() -> {

      try {

        if (!task.startAttempt()) {
          return null;
        }

        repository.update(task);

        task.getAction().run();

        task.markCompleted();

        repository.update(task);

        System.out.println(
            "Task completed: " + task.getTaskId()
        );

      } catch (Exception e) {

        if (task.isCancellationRequested()
            || Thread.currentThread().isInterrupted()) {

          task.requestCancellation();

        } else {

          retryManager.handleFailure(task, e);
        }

        repository.update(task);

      } finally {

        runningTasks.remove(task.getTaskId());
      }

      return null;
    });

    try {

      runningTasks.put(task.getTaskId(), future);

      workerPool.execute(future);

    } catch (RejectedExecutionException e) {

      runningTasks.remove(task.getTaskId());

      retryManager.handleFailure(task, e);
    }
  }

  public void cancel(String taskId) {

    Future<?> future = runningTasks.get(taskId);

    if (future != null) {
      future.cancel(true);
    }
  }

  public void shutdown() {

    workerPool.shutdown();
  }
}

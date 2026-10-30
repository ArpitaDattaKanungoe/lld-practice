package main.DesignPractice.TaskExecution;

import java.time.Duration;
import java.util.concurrent.*;

class TaskScheduler {

  private final ScheduledExecutorService scheduler =
      Executors.newScheduledThreadPool(2);

  private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledTasks =
      new ConcurrentHashMap<>();

  private TaskExecutor executor;

  public void setTaskExecutor(TaskExecutor executor) {
    this.executor = executor;
  }

  public void schedule(Task task, Duration delay) {

    if (task.isCancellationRequested()) {
      return;
    }

    ScheduledFuture<?> future = scheduler.schedule(
        () -> {

          scheduledTasks.remove(task.getTaskId());

          if (!task.isCancellationRequested()) {
            executor.execute(task);
          }

        },
        delay.toMillis(),
        TimeUnit.MILLISECONDS
    );

    ScheduledFuture<?> previous =
        scheduledTasks.put(task.getTaskId(), future);

    if (previous != null) {
      previous.cancel(false);
    }

    // Recheck cancellation in case it arrived during scheduling.
    if (task.isCancellationRequested()) {
      cancel(task.getTaskId());
    }
  }

  public void cancel(String taskId) {

    ScheduledFuture<?> future =
        scheduledTasks.remove(taskId);

    if (future != null) {
      future.cancel(false);
    }
  }

  public void shutdown() {

    scheduler.shutdown();
  }
}

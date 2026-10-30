<img width="3032" height="2110" alt="mermaid-diagram (11)" src="https://github.com/user-attachments/assets/5b3bbeb4-14f1-49e8-99e9-9b73e9d93254" />

```
Client
  |
  v
TaskManager
  |
  v
TaskRepository (save)
  |
  v
TaskScheduler
  |
  v
TaskExecutor
  |
  +---- SUCCESS ----> COMPLETED
  |
  +---- FAILURE ----> RetryManager
                         |
                         +---- Retry available
                         |        |
                         |        v
                         |    RetryPolicy
                         |        |
                         |        v
                         |    TaskScheduler
                         |
                         +---- No retries
                                  |
                                  v
                             EXHAUSTED
                                  |
                                  v
                            FailureHandler
```

<img width="2982" height="3512" alt="mermaid-diagram (12)" src="https://github.com/user-attachments/assets/4745fda3-7c76-491e-944a-1624cdbcf7ec" />

How to read the diagram
- TaskManager is the entry point. It coordinates submission, cancellation, status checks, and shutdown.
- Task represents one submitted unit of work and maintains its execution state.
- TaskScheduler decides when a task should run.
- TaskExecutor decides where/how it runs, using a worker thread pool.
- RetryManager decides whether a failed task should be retried and calculates its next execution time.
- RetryPolicy defines the retry configuration, such as maximum attempts and exponential backoff.
- TaskRepository stores task state.
- FailureHandler handles tasks that have exhausted their retries.


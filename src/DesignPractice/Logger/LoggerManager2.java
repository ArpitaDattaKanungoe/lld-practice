package DesignPractice.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerManager2 {

  private static LoggerManager2 INSTANCE;

  private final LoggerConfig config;
  private final Map<String, Logger2> loggers = new ConcurrentHashMap<>();

  private LoggerManager2(LoggerConfig config) {
    this.config = config;
  }

  public static synchronized void initialize(LoggerConfig config) { // synchronized : INSTANCE is shared by all threads
    if (INSTANCE == null) {
      INSTANCE = new LoggerManager2(config);
    }
  }

  public static synchronized LoggerManager2 getInstance() { // synchronized : INSTANCE is shared by all threads

    // First time -> create with default configuration
    if (INSTANCE == null) {
      INSTANCE = new LoggerManager2(defaultConfig());
    }

    return INSTANCE;
  }

  public Logger2 getLogger(String name) {
    return loggers.computeIfAbsent(
        name,
        key -> new Logger2(key, config)
    );
  }

  private static LoggerConfig defaultConfig() {

    LoggerConfig config = new LoggerConfig(LogLevel.Error);

    Formatter formatter = new DefaultFormatter();

    config.addAppender(
        LogLevel.Info,
        new ConsoleAppender(formatter));

    config.addAppender(
        LogLevel.Warn,
        new ConsoleAppender(formatter));

    config.addAppender(
        LogLevel.Error,
        new ConsoleAppender(formatter));

    return config;
  }
}

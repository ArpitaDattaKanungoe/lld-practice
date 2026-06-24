package main.DesignPractice.Logger;

public class Logger2 {

  private final String name;
  private final LoggerConfig config;

  public Logger2(String name, LoggerConfig config) {
    this.name = name;
    this.config = config;
  }

  private void log(LogLevel level, String message) {

    // Ignore messages below configured level
    if (level.ordinal() < config.getRootLevel().ordinal()) {
      return;
    }

    LogMessage logMessage = new LogMessage(level, message, name);

    for (Appender appender : config.getAppenders(level)) {
      appender.append(logMessage);
    }
  }

  public void setConfigLevel(LogLevel rootLevel){
    config.setRootLevel(rootLevel);
  }

  public void debug(String message) {
    log(LogLevel.Debug, message);
  }

  public void info(String message) {
    log(LogLevel.Info, message);
  }

  public void warn(String message) {
    log(LogLevel.Warn, message);
  }

  public void error(String message) {
    log(LogLevel.Error, message);
  }
}

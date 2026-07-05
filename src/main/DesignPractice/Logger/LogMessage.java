package main.DesignPractice.Logger;

import java.time.LocalDateTime;

public class LogMessage {
  private LogLevel logLevel;
  private String message;
  private String source;
  private LocalDateTime localTime;

  public LogMessage(LogLevel logLevel, String message
      ,String source) {
    this.logLevel = logLevel;
    this.message = message;
    this.source = source;
    this.localTime = LocalDateTime.now();
  }

  public LogLevel getLogLevel() {
    return logLevel;
  }

  public String getMessage() {
    return message;
  }

  public LocalDateTime getLocalTime() {
    return localTime;
  }

  public String getSource() {
    return source;
  }
}

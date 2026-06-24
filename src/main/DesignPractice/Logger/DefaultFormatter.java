package main.DesignPractice.Logger;

import java.time.format.DateTimeFormatter;

public class DefaultFormatter implements Formatter {

  @Override
  public String format(LogMessage logMessage) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return String.format("%s %s [%s] %s]", logMessage.getLocalTime().format(formatter), logMessage.getLogLevel(),
        logMessage.getMessage(), logMessage.getSource());
  }
}

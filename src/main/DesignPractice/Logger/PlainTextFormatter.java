package main.DesignPractice.Logger;

import java.time.format.DateTimeFormatter;

public class PlainTextFormatter implements Formatter {
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @Override
  public String format(LogMessage message) {
    String formattedTime = message.getLocalTime().format(FORMATTER);

    return String.format("%s [%s] - %s", formattedTime, message.getLogLevel(), message.getMessage());
  }
}
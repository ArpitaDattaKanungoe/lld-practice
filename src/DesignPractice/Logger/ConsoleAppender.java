package DesignPractice.Logger;

public class ConsoleAppender implements Appender {
  Formatter formatter;

  public ConsoleAppender(Formatter formatter) {
    this.formatter = formatter;
  }

  @Override
  public void append(LogMessage logMessage) {
    String formattedMessage = formatter.format(logMessage);
    System.out.println(formattedMessage);
  }
}

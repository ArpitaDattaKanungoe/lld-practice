package DesignPractice.Logger;

public class FileAppender implements Appender {

  Formatter formatter;

  public FileAppender(Formatter formatter) {
    this.formatter = formatter;
  }

  @Override
  public void append(LogMessage logMessage) {

  }
}

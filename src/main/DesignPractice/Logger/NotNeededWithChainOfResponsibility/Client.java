package main.DesignPractice.Logger.NotNeededWithChainOfResponsibility;

import main.DesignPractice.Logger.ConsoleAppender;
import main.DesignPractice.Logger.FileAppender;
import main.DesignPractice.Logger.LogLevel;
import main.DesignPractice.Logger.PlainTextFormatter;

public class Client {
  public static void main(String[] args) {
    Logger logger = Logger.getInstance();

    LogHandlerConfiguration.addAppenderForLevel(
        LogLevel.Info,
        new ConsoleAppender(new PlainTextFormatter())
    );

    LogHandlerConfiguration.addAppenderForLevel(
        LogLevel.Error,
        new ConsoleAppender(new PlainTextFormatter())
    );

    LogHandlerConfiguration.addAppenderForLevel(
        LogLevel.Error,
        new FileAppender("logs.txt",new PlainTextFormatter())
    );

    // Usage
    logger.info("This is some key information"); // CONSOLE
    logger.error("Oh no! there's an error"); // CONSOLE + FILE
  }
}

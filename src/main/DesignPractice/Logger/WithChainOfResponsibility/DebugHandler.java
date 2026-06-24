package main.DesignPractice.Logger.WithChainOfResponsibility;

import main.DesignPractice.Logger.LogLevel;

public class DebugHandler extends LogHandler {

  @Override
  boolean canHandle(LogLevel logLevel) {
    return logLevel == LogLevel.Debug;
  }
}

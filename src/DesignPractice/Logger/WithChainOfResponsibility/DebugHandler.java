package DesignPractice.Logger.WithChainOfResponsibility;

import DesignPractice.Logger.LogLevel;

public class DebugHandler extends LogHandler {

  @Override
  boolean canHandle(LogLevel logLevel) {
    return logLevel == LogLevel.Debug;
  }
}

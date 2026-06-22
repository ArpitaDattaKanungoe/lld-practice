package DesignPractice.Logger.WithChainOfResponsibility;

import DesignPractice.Logger.LogLevel;

public class ErrorHandler extends LogHandler {

  @Override
  boolean canHandle(LogLevel logLevel) {
    return logLevel==LogLevel.Error;
  }
}

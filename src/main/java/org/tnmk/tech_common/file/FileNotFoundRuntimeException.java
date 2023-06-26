package org.tnmk.tech_common.file;

public class FileNotFoundRuntimeException extends RuntimeException {
  public FileNotFoundRuntimeException(String message) {
    super(message);
  }
  public FileNotFoundRuntimeException(String message, Throwable throwable) {
    super(message, throwable);
  }
}

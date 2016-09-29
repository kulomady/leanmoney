/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.exception;


/**
 * Wrapper around Exceptions used to manage errors in the repository.
 */
public class RepositoryErrorBundle implements com.hack.domain.exception.ErrorBundle {

  private final Exception exception;

  public RepositoryErrorBundle(Exception exception) {
    this.exception = exception;
  }

  @Override
  public Exception getException() {
    return exception;
  }

  @Override
  public String getErrorMessage() {
    String message = "";
    if (this.exception != null) {
      this.exception.getMessage();
    }
    return message;
  }
}

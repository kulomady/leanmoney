/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain.exception;

/**
 *  Wrapper around Exceptions used to manage default errors.
 */
public class DefaultErrorBundle implements ErrorBundle {

  private static final String DEFAULT_ERROR_MSG = "Unknown error";

  private final Exception exception;

  public DefaultErrorBundle(Exception exception) {
    this.exception = exception;
  }

  @Override
  public Exception getException() {
    return exception;
  }

  @Override
  public String getErrorMessage() {
    return (exception != null) ? this.exception.getMessage() : DEFAULT_ERROR_MSG;
  }
}

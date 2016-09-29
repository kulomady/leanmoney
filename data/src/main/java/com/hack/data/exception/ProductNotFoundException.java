/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.exception;

/**
 * Exception throw by the application when a Product search can't return a valid result.
 */
public class ProductNotFoundException extends Exception {

  public ProductNotFoundException() {
    super();
  }

  public ProductNotFoundException(final String message) {
    super(message);
  }

  public ProductNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ProductNotFoundException(final Throwable cause) {
    super(cause);
  }
}

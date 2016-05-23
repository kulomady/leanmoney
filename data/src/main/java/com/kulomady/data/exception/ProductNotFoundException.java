package com.kulomady.data.exception;

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

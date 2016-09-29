/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain.interactor;

/**
 * Default subscriber base class to be used whenever we want default error handling.
 */
public class DefaultSubscriber<T> extends rx.Subscriber<T> {
  @Override public void onCompleted() {
    // no-op by default.
  }

  @Override public void onError(Throwable e) {
    // no-op by default.
  }

  @Override public void onNext(T t) {
    // no-op by default.
  }
}

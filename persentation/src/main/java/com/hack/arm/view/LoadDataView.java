/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.view;

import android.content.Context;

/**
 * Interface representing a View that will use to load data.
 */
public interface LoadDataView {
  void showLoading();

  void hideLoading();

  void showRetry();

  void hideRetry();

  void showError(String message);

  Context context();
}

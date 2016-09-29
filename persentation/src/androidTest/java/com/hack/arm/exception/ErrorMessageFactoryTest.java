/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.exception;

import android.test.AndroidTestCase;

import com.hack.data.exception.NetworkConnectionException;
import com.hack.data.exception.ProductNotFoundException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ErrorMessageFactoryTest extends AndroidTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  public void testNetworkConnectionErrorMessage() {
    String expectedMessage = getContext().getString(com.hack.arm.R.string.exception_message_no_connection);
    String actualMessage = ErrorMessageFactory.create(getContext(),
        new NetworkConnectionException());

    assertThat(actualMessage, is(equalTo(expectedMessage)));
  }

  public void testUserNotFoundErrorMessage() {
    String expectedMessage = getContext().getString(com.hack.arm.R.string.exception_message_user_not_found);
    String actualMessage = ErrorMessageFactory.create(getContext(), new ProductNotFoundException());

    assertThat(actualMessage, is(equalTo(expectedMessage)));
  }
}

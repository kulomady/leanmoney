/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.exception;

import com.hack.data.ApplicationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class RepositoryErrorBundleTest extends ApplicationTestCase {

  private RepositoryErrorBundle repositoryErrorBundle;

  @Mock
  private Exception mockException;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    repositoryErrorBundle = new RepositoryErrorBundle(mockException);
  }

  @Test
  public void testGetErrorMessageInteraction() {
    repositoryErrorBundle.getErrorMessage();

    verify(mockException).getMessage();
  }
}


/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain.interactor;


import com.hack.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetProductDetailsTest {

  private static final int FAKE_USER_ID = 123;

  private GetDetails getProductDetails;

  @Mock private com.hack.domain.repository.ProductRepository mockProductRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private com.hack.domain.executor.PostExecutionThread mockPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getProductDetails = new GetDetails(FAKE_USER_ID, mockProductRepository,
        mockThreadExecutor, mockPostExecutionThread);
  }

  @Test
  public void testGetProductDetailsUseCaseObservableHappyCase() {
    getProductDetails.buildUseCaseObservable();

    verify(mockProductRepository).product(FAKE_USER_ID);
    verifyNoMoreInteractions(mockProductRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);
  }
}

/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.hack.arm.mapper.ProductModelDataMapper;
import com.hack.domain.interactor.GetDetails;
import com.hack.arm.view.ProductDetailsView;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class ProductDetailsPresenterTest extends AndroidTestCase {

  private ProductDetailsPresenter userDetailsPresenter;

  @Mock
  private Context mockContext;
  @Mock
  private ProductDetailsView mockUserDetailsView;
  @Mock
  private GetDetails mockGetUserDetails;
  @Mock
  private ProductModelDataMapper mockUserModelDataMapper;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    MockitoAnnotations.initMocks(this);
    userDetailsPresenter = new ProductDetailsPresenter(mockGetUserDetails,
        mockUserModelDataMapper);
    userDetailsPresenter.setView(mockUserDetailsView);
  }

  public void testUserDetailsPresenterInitialize() {
    given(mockUserDetailsView.context()).willReturn(mockContext);

    userDetailsPresenter.initialize();

    verify(mockUserDetailsView).hideRetry();
    verify(mockUserDetailsView).showLoading();
    verify(mockGetUserDetails).execute(any(Subscriber.class));
  }
}

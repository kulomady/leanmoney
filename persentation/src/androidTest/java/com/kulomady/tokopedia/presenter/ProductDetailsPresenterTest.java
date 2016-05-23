package com.kulomady.tokopedia.presenter;

import android.content.Context;
import android.test.AndroidTestCase;

import com.kulomady.domain.interactor.GetDetails;
import com.kulomady.tokopedia.mapper.ProductModelDataMapper;
import com.kulomady.tokopedia.view.ProductDetailsView;

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

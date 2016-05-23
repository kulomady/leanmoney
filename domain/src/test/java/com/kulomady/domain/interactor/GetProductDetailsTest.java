
package com.kulomady.domain.interactor;


import com.kulomady.domain.executor.PostExecutionThread;
import com.kulomady.domain.executor.ThreadExecutor;
import com.kulomady.domain.repository.ProductRepository;

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

  @Mock private ProductRepository mockProductRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

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

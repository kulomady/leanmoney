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

public class GetProductListTest {

  private GetList getProductList;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private ProductRepository mockProductRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getProductList = new GetList("mouse lenovo",0,"android",10,mockProductRepository, mockThreadExecutor,
        mockPostExecutionThread);
  }

  @Test
  public void testGetProductListUseCaseObservableHappyCase() {
    getProductList.buildUseCaseObservable();
    verify(mockProductRepository).searchProduct("mouse lenovo", 0, 10, "android");
    verifyNoMoreInteractions(mockProductRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}

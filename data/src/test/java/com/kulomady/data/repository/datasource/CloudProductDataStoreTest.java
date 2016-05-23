package com.kulomady.data.repository.datasource;

import com.kulomady.data.ApplicationTestCase;
import com.kulomady.data.cache.ProductCache;
import com.kulomady.data.entity.response.ProductBean;
import com.kulomady.data.net.ProductRestApi;
import com.kulomady.data.repository.datastore.productDataStore.CloudProductDataStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CloudProductDataStoreTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 765;

  private CloudProductDataStore cloudProductDataStore;

  @Mock private ProductRestApi mockRestApi;
  @Mock private ProductCache mockProductCache;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    cloudProductDataStore = new CloudProductDataStore(mockRestApi, mockProductCache);
  }

  @Test
  public void testGetProductEntityListFromApi() {
//    cloudProductDataStore.productEntityList("tas");
//    verify(mockRestApi).productEntityList("test");
  }

  @Test
  public void testGetProductEntityDetailsFromApi() {
    ProductBean fakeProductEntity = new ProductBean();
    Observable<ProductBean> fakeObservable = Observable.just(fakeProductEntity);
    given(mockRestApi.productEntityById(FAKE_USER_ID)).willReturn(fakeObservable);

    cloudProductDataStore.productEntityDetails(FAKE_USER_ID);

    verify(mockRestApi).productEntityById(FAKE_USER_ID);
  }
}

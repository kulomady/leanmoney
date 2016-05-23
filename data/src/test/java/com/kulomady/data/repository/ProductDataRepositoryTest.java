
package com.kulomady.data.repository;

import com.kulomady.data.ApplicationTestCase;
import com.kulomady.data.entity.mapper.ProductEntityDataMapper;
import com.kulomady.data.entity.response.DataProductEntity;
import com.kulomady.data.entity.response.ProductBean;
import com.kulomady.data.repository.datastore.productDataStore.ProductDataStore;
import com.kulomady.data.repository.datastore.productDataStore.ProductDataStoreFactory;
import com.kulomady.domain.item.ProductItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

public class ProductDataRepositoryTest extends ApplicationTestCase {

  private static final int FAKE_PRODUCT_ID = 123;

  private ProductDataRepository productDataRepository;

  @Mock private ProductDataStoreFactory mockProductDataStoreFactory;
  @Mock private ProductEntityDataMapper mockProductEntityDataMapper;
  @Mock private ProductDataStore mockProductDataStore;
  @Mock private ProductBean mockProductEntity;
  @Mock private ProductItem mockProduct;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productDataRepository = new ProductDataRepository(mockProductDataStoreFactory,
        mockProductEntityDataMapper);

    given(mockProductDataStoreFactory.create(anyInt())).willReturn(mockProductDataStore);
    given(mockProductDataStoreFactory.createCloudDataStore()).willReturn(mockProductDataStore);
  }

  @Test
  public void testGetProductsHappyCase() {
    List<ProductBean> productsList = new ArrayList<>();
    DataProductEntity dataProductEntity = new DataProductEntity();
    given(mockProductDataStore
            .productEntityList1("mouse lenovo",0,10,"android"))
            .willReturn(Observable.just(dataProductEntity));

    productDataRepository.searchProduct("mouse lenovo",0,10,"android");

    verify(mockProductDataStoreFactory).createCloudDataStore();
    verify(mockProductDataStore).productEntityList1("mouse lenovo",0,10,"android");
  }

  @Test
  public void testGetProductHappyCase() {
    ProductBean productEntity =  new ProductBean();
    given(mockProductDataStore.productEntityDetails(FAKE_PRODUCT_ID)).willReturn(Observable.just(productEntity));
    productDataRepository.product(FAKE_PRODUCT_ID);

    verify(mockProductDataStoreFactory).create(FAKE_PRODUCT_ID);
    verify(mockProductDataStore).productEntityDetails(FAKE_PRODUCT_ID);
  }
}

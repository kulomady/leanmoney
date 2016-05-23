package com.kulomady.data.repository.datasource;

import com.kulomady.data.ApplicationTestCase;
import com.kulomady.data.cache.ProductCache;
import com.kulomady.data.repository.datastore.productDataStore.DiskProductDataStore;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DiskProductDataStoreTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 11;

  private DiskProductDataStore diskProductDataStore;

  @Mock private ProductCache mockProductCache;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    diskProductDataStore = new DiskProductDataStore(mockProductCache);
  }

  @Test
  public void testGetProductEntityListUnsupported() {
    expectedException.expect(UnsupportedOperationException.class);
    diskProductDataStore.productEntityList1("tas",0,10,"android");
  }

  @Test
  public void testGetProductEntityDetailesFromCache() {
    diskProductDataStore.productEntityDetails(FAKE_USER_ID);
    verify(mockProductCache).get(FAKE_USER_ID);
  }
}

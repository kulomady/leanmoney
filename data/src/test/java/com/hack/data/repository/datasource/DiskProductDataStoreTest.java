/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.repository.datasource;

import com.hack.data.cache.ProductCache;
import com.hack.data.repository.datastore.productDataStore.DiskProductDataStore;
import com.hack.data.ApplicationTestCase;

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

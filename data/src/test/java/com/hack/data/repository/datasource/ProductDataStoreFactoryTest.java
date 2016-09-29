/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.repository.datasource;


import com.hack.data.ApplicationTestCase;
import com.hack.data.cache.ProductCache;
import com.hack.data.repository.datastore.productDataStore.CloudProductDataStore;
import com.hack.data.repository.datastore.productDataStore.DiskProductDataStore;
import com.hack.data.repository.datastore.productDataStore.ProductDataStore;
import com.hack.data.repository.datastore.productDataStore.ProductDataStoreFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ProductDataStoreFactoryTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 11;

  private ProductDataStoreFactory productDataStoreFactory;

  @Mock
  private ProductCache mockProductCache;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productDataStoreFactory =
        new ProductDataStoreFactory(RuntimeEnvironment.application, mockProductCache);
  }

  @Test
  public void testCreateDiskDataStore() {
    given(mockProductCache.isCached(FAKE_USER_ID)).willReturn(true);
    given(mockProductCache.isExpired()).willReturn(false);

    ProductDataStore productDataStore = productDataStoreFactory.create(FAKE_USER_ID);

    assertThat(productDataStore, is(notNullValue()));
    assertThat(productDataStore, is(instanceOf(DiskProductDataStore.class)));

    verify(mockProductCache).isCached(FAKE_USER_ID);
    verify(mockProductCache).isExpired();
  }

  @Test
  public void testCreateCloudDataStore() {
    given(mockProductCache.isExpired()).willReturn(true);
    given(mockProductCache.isCached(FAKE_USER_ID)).willReturn(false);

    ProductDataStore productDataStore = productDataStoreFactory.create(FAKE_USER_ID);

    assertThat(productDataStore, is(notNullValue()));
    assertThat(productDataStore, is(instanceOf(CloudProductDataStore.class)));

    verify(mockProductCache).isExpired();
  }
}

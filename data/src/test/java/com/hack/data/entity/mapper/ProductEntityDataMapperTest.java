/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.entity.mapper;


import com.hack.data.entity.response.DataProductEntity;
import com.hack.data.entity.response.ProductBean;
import com.hack.data.ApplicationTestCase;
import com.hack.domain.item.ProductItem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductEntityDataMapperTest extends ApplicationTestCase {

  private static final int FAKE_PRODUCT_ID = 123;
  private static final String FAKE_PRODUCT_NAME = "Kulomady";

  private ProductEntityDataMapper userEntityDataMapper;

  @Before
  public void setUp() throws Exception {
    userEntityDataMapper = new ProductEntityDataMapper();
  }

  @Test
  public void testTransformUserEntity() {
    ProductBean userEntity = createFakeUserEntity();
    ProductItem user = userEntityDataMapper.transform(userEntity);

    assertThat(user, is(instanceOf(ProductItem.class)));
    assertThat(user.getProduct_id(), is(FAKE_PRODUCT_ID));
    assertThat(user.getProduct_name(), is(FAKE_PRODUCT_NAME));
  }

  @Test
  public void testTransformUserEntityCollection() {
    DataProductEntity dataProductEntity = new DataProductEntity();

    com.hack.domain.ProductDomain userCollection = userEntityDataMapper.transform(dataProductEntity);

//    assertThat(userCollection.toArray()[0], is(instanceOf(ProductItem.class)));
//    assertThat(userCollection.toArray()[1], is(instanceOf(ProductItem.class)));
//    assertThat(userCollection.size(), is(2));
  }

  private ProductBean createFakeUserEntity() {
    ProductBean userEntity = new ProductBean();
    userEntity.setProduct_id(FAKE_PRODUCT_ID);
    userEntity.setProduct_name(FAKE_PRODUCT_NAME);

    return userEntity;
  }
}

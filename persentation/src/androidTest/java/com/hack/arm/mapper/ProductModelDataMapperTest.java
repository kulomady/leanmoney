/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.mapper;


import com.hack.domain.ProductDomain;
import com.hack.domain.item.ProductItem;
import com.hack.arm.model.ProductModel;

import junit.framework.TestCase;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ProductModelDataMapperTest extends TestCase {

  private static final int FAKE_PRODUCT_ID = 123;
  private static final String FAKE_PRODUCT_NAME = "Tony Stark";

  private ProductModelDataMapper productModelDataMapper;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    productModelDataMapper = new ProductModelDataMapper();
  }

  public void testTransformUser() {
    ProductItem product = createFakeUser();
    ProductModel productModel = productModelDataMapper.transform(product);

    assertThat(productModel, is(instanceOf(ProductModel.class)));
    assertThat(productModel.getProduct_id(), is(FAKE_PRODUCT_ID));
    assertThat(productModel.getProduct_name(), is(FAKE_PRODUCT_NAME));
  }

  public void testTransformUserCollection() {
    ProductDomain mockProductDomain = mock(ProductDomain.class);



    Collection<ProductModel> productModelList = productModelDataMapper.transform(mockProductDomain);
    assertThat(productModelList.toArray()[0], is(instanceOf(ProductModel.class)));

  }

  private ProductItem createFakeUser() {
    ProductItem product = new ProductItem();
    product.setProduct_id(FAKE_PRODUCT_ID);
    product.setProduct_name(FAKE_PRODUCT_NAME);

    return product;
  }
}

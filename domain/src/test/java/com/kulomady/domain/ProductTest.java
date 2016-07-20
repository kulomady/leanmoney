package com.kulomady.domain;

import com.kulomady.domain.item.ProductItem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductTest {

  private static final int FAKE_PRODUCT_ID = 8;

  private ProductItem product;

  @Before
  public void setUp() {
    product = new ProductItem();
    product.setProduct_id(FAKE_PRODUCT_ID);

  }

  @Test
  public void testProductConstructorHappyCase() {
    int userId = product.getProduct_id();

    assertThat(userId, is(FAKE_PRODUCT_ID));
  }
}

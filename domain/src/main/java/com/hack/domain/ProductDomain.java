/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain;

/**
 * Class that represents a Product in the domain layer.
 */
public class ProductDomain {
  private com.hack.domain.item.ProductWrapper result;

  public com.hack.domain.item.ProductWrapper getResult() {
    return result;
  }

  public void setResult(com.hack.domain.item.ProductWrapper result) {
    this.result = result;
  }

}

package com.kulomady.domain;

import com.kulomady.domain.item.ProductWrapper;

/**
 * Class that represents a Product in the domain layer.
 */
public class ProductDomain {
  private ProductWrapper result;

  public ProductWrapper getResult() {
    return result;
  }

  public void setResult(ProductWrapper result) {
    this.result = result;
  }

}

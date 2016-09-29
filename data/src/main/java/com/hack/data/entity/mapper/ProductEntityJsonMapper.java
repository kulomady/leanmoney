/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.entity.mapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hack.data.entity.response.ProductBean;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class ProductEntityJsonMapper {

  private final Gson gson;

  @Inject
  public ProductEntityJsonMapper() {
    this.gson = new Gson();
  }


  public ProductBean transformUserEntity(String productJsonResponse) throws JsonSyntaxException {
    try {
      Type userEntityType = new TypeToken<ProductBean>() {}.getType();
      ProductBean productEntity = this.gson.fromJson(productJsonResponse, userEntityType);

      return productEntity;
    } catch (JsonSyntaxException jsonException) {
      throw jsonException;
    }
  }

  public List<ProductBean> transformUserEntityCollection(String productListJsonResponse)
      throws JsonSyntaxException {

    List<ProductBean> productEntityCollection;
    try {
      Type listOfProductEntityType = new TypeToken<List<ProductBean>>() {}.getType();
      productEntityCollection = this.gson.fromJson(productListJsonResponse, listOfProductEntityType);

      return productEntityCollection;
    } catch (JsonSyntaxException jsonException) {
      throw jsonException;
    }
  }
}

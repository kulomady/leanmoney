/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.mapper;


import com.google.gson.Gson;
import com.hack.domain.ProductDomain;
import com.hack.domain.item.ProductItem;
import com.hack.domain.item.ProductWrapper;
import com.hack.arm.internal.di.PerActivity;
import com.hack.arm.model.ProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link ProductItem} (in the domain layer) to {@link ProductModel} in the
 * presentation layer.
 */
@PerActivity
public class ProductModelDataMapper {

    @Inject
    public ProductModelDataMapper() {
    }
    public ProductModel transform(ProductItem product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Gson gson = new Gson();
        String tempJson = gson.toJson(product);
        return gson.fromJson(tempJson, ProductModel.class);
    }

    public Collection<ProductModel> transform(ProductDomain productDomain) {
        Collection<ProductModel> productModelsCollection;
        Gson gson = new Gson();
        ProductWrapper productResult = productDomain.getResult();
        if (productResult != null) {
            productModelsCollection = new ArrayList<>();
            if (!productResult.getProducts().isEmpty()) {
                for (ProductItem product :
                        productDomain.getResult().getProducts()) {
                    String tempJson = gson.toJson(product);
                    ProductModel productModel = gson.fromJson(tempJson, ProductModel.class);
                    productModelsCollection.add(productModel);
                }
            } else {
                productModelsCollection = Collections.emptyList();
            }
        } else {
            productModelsCollection = Collections.emptyList();
        }

        return productModelsCollection;
    }
}

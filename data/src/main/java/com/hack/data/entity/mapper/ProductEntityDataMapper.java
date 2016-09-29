
/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.entity.mapper;

import android.util.Log;

import com.google.gson.Gson;
import com.hack.data.entity.response.DataProductEntity;
import com.hack.data.entity.response.ProductBean;
import com.hack.domain.ProductDomain;
import com.hack.domain.item.ProductItem;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ProductBean} (in the data layer) to {@link ProductItem} in the
 * domain layer.
 */
@Singleton
public class ProductEntityDataMapper {

    private static final String TAG = "ProductEntityDataMapper";

    @Inject
    public ProductEntityDataMapper() {
    }

    public ProductItem transform(ProductBean productEntity) {
        ProductItem product;
        if (productEntity != null) {
            Gson gson = new Gson();
           String tempJson = gson.toJson(productEntity);
            product = gson.fromJson(tempJson, ProductItem.class);
        }else{
            product = new ProductItem();
        }

        return product;
    }

    public ProductDomain transform(DataProductEntity productEntity) {
        Log.d(TAG, "transform() called with: " + "productEntity = [" + new Gson().toJson(productEntity) + "]");
        ProductDomain product;
        if (productEntity != null) {
            String tempJson = new Gson().toJson(productEntity);
            Log.d(TAG, "transform: " + tempJson);
            product = new Gson().fromJson(tempJson,ProductDomain.class);
            Log.d(TAG, "transform after convert" + new Gson().toJson(product));
        }
        else{
            product = new ProductDomain();
        }

        return product;
    }


}

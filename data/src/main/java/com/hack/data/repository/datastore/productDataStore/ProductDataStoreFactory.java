/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.repository.datastore.productDataStore;

import android.content.Context;

import com.hack.data.cache.ProductCache;
import com.hack.data.entity.mapper.ProductEntityJsonMapper;
import com.hack.data.net.ProductRestApi;

import com.hack.data.net.ProductRestApiImpl;

import javax.inject.Inject;

/**
 * Factory that creates different implementations of {@link ProductDataStore}.
 * Created by kulomady on 5/6/16.
 */
public class ProductDataStoreFactory {
    private final Context context;
    private final ProductCache productCache;

    @Inject
    public ProductDataStoreFactory(Context context, ProductCache productCache) {
        if (context == null || productCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.productCache = productCache;
    }


    public ProductDataStore create(int userId) {
        ProductDataStore productDataStore;

        if (!this.productCache.isExpired() && this.productCache.isCached(userId)) {
            productDataStore = new DiskProductDataStore(this.productCache);
        } else {
            productDataStore = createCloudDataStore();
        }

        return productDataStore;
    }

    public ProductDataStore createCloudDataStore() {
        ProductEntityJsonMapper productEntityJsonMapper = new ProductEntityJsonMapper();

        ProductRestApi restApi = new ProductRestApiImpl(this.context, productEntityJsonMapper);

        return new CloudProductDataStore(restApi, this.productCache);
    }
}

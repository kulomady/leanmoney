/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.repository.datastore.productDataStore;

import com.hack.data.cache.ProductCache;
import com.hack.data.entity.response.DataProductEntity;
import com.hack.data.entity.response.ProductBean;

import rx.Observable;

/**
 * {@link ProductDataStore} implementation based on file system data store.
 */
public class DiskProductDataStore implements ProductDataStore {

    private final ProductCache productCache;
    public DiskProductDataStore(ProductCache productCache) {
        this.productCache = productCache;
    }

    @Override
    public Observable<ProductBean> productEntityDetails(final int productId) {
        return this.productCache.get(productId);
    }

    @Override
    public Observable<DataProductEntity> productEntityList1(String queryValue, int start,int rows,String device) {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }
}

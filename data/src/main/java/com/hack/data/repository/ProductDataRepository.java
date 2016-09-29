/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.repository;

import com.hack.data.entity.mapper.ProductEntityDataMapper;
import com.hack.data.repository.datastore.productDataStore.ProductDataStore;
import com.hack.data.repository.datastore.productDataStore.ProductDataStoreFactory;
import com.hack.domain.ProductDomain;
import com.hack.domain.item.ProductItem;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 *
 * Created by kulomady on 5/6/16.
 * {@link com.hack.domain.repository.ProductRepository} for retrieving product data.
 */
@Singleton
public class ProductDataRepository implements com.hack.domain.repository.ProductRepository {

    private final ProductDataStoreFactory productDataStoreFactory;
    private final ProductEntityDataMapper productEntityDataMapper;

    @Inject
    public ProductDataRepository(ProductDataStoreFactory dataStoreFactory,
                                 ProductEntityDataMapper productEntityDataMapper) {
        this.productDataStoreFactory = dataStoreFactory;
        this.productEntityDataMapper = productEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<ProductDomain> searchProduct(String queryValue, int start,int rows,String device) {
        //always get all products from the cloud
        final ProductDataStore productDataStore = this.productDataStoreFactory.createCloudDataStore();
        return productDataStore.productEntityList1(queryValue,start,rows,device)
                .map(dataProductEntity -> this.productEntityDataMapper.transform(dataProductEntity));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override public Observable<ProductItem> product(int productId) {
        final ProductDataStore productDataStore = this.productDataStoreFactory.create(productId);
        return productDataStore.productEntityDetails(productId)
                .map(productEntity -> this.productEntityDataMapper.transform(productEntity));
    }
}

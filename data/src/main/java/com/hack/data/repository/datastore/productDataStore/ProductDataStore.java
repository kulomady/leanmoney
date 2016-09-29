/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.repository.datastore.productDataStore;

import com.hack.data.entity.response.DataProductEntity;
import com.hack.data.entity.response.ProductBean;

import rx.Observable;


/**
 * Interface that represents a data store from where data is retrieved.
 * Created by kulomady on 5/6/16.
 */
public interface ProductDataStore {


    Observable<ProductBean> productEntityDetails(final int productId);

    Observable<DataProductEntity> productEntityList1(String queryValue, int start, int rows, String device);
}

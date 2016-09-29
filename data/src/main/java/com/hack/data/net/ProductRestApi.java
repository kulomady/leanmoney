/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.net;


import com.hack.data.entity.response.DataProductEntity;
import com.hack.data.entity.response.ProductBean;

import rx.Observable;

/**
 * ProductRestApi for retrieving product data from the network.
 */
public interface ProductRestApi {

    Observable<ProductBean> productEntityById(final int userId);

    Observable<DataProductEntity> productEntityList1(String queryValue, int start, int rows, String device);
}

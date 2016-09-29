/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.hack.data.entity.mapper.ProductEntityJsonMapper;
import com.hack.data.entity.response.DataProductEntity;
import com.hack.data.entity.response.ProductBean;

import rx.Observable;

/**
 * {@link ProductRestApi} implementation for retrieving data from the network.
 */
public class ProductRestApiImpl implements ProductRestApi {

    private final Context context;
    private final ProductEntityJsonMapper productEntityJsonMapper;
    private final ProductService productService;

    public ProductRestApiImpl(Context context, ProductEntityJsonMapper productEntityJsonMapper) {
        if (context == null || productEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.productEntityJsonMapper = productEntityJsonMapper;
        this.productService = new ProductService();
    }

    @RxLogObservable
    @Override
    public Observable<ProductBean> productEntityById(final int userId) {
        String userIdParams = "user_" + userId + ".json";
        return productService.getApi().productEntityById(userIdParams);
    }

    @RxLogObservable
    @Override
    public Observable<DataProductEntity> productEntityList1(String queryValue, int start, int rows, String device) {

        return productService.getApi().productEntityList1(
                queryValue,
                device,
                start,
                rows
        );
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}

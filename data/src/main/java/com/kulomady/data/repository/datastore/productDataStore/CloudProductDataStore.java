package com.kulomady.data.repository.datastore.productDataStore;

import com.kulomady.data.cache.ProductCache;
import com.kulomady.data.entity.response.DataProductEntity;
import com.kulomady.data.entity.response.ProductBean;
import com.kulomady.data.net.ProductRestApi;

import rx.Observable;
import rx.functions.Action1;

/**
 *
 * Created by kulomady on 5/6/16.
 */
public class CloudProductDataStore implements ProductDataStore {
    private final ProductRestApi restApi;
    private final ProductCache productCache;

    private final Action1<ProductBean> saveToCacheAction = userEntity ->{
        if (userEntity != null) {
            CloudProductDataStore.this.productCache.put(userEntity);
        }
    };

    public CloudProductDataStore(ProductRestApi restApi, ProductCache productCache) {
        this.restApi = restApi;
        this.productCache = productCache;
    }

    @Override
    public Observable<ProductBean> productEntityDetails(int productId) {
        return restApi.productEntityById(productId).doOnNext(saveToCacheAction);
    }

    @Override
    public Observable<DataProductEntity> productEntityList1(String queryValue, int start,int rows,String device) {
        return this.restApi.productEntityList1(queryValue,start,rows,device);
    }
}

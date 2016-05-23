package com.kulomady.data.repository.datastore.productDataStore;

import com.kulomady.data.cache.ProductCache;
import com.kulomady.data.entity.response.DataProductEntity;
import com.kulomady.data.entity.response.ProductBean;

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

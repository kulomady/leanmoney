package com.kulomady.data.repository.datastore.productDataStore;

import android.content.Context;

import com.kulomady.data.cache.ProductCache;

import com.kulomady.data.net.ProductRestApi;
import com.kulomady.data.entity.mapper.ProductEntityJsonMapper;
import com.kulomady.data.net.ProductRestApiImpl;

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

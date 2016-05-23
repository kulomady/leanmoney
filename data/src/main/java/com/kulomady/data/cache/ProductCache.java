package com.kulomady.data.cache;

import com.kulomady.data.entity.response.ProductBean;

import rx.Observable;

/**
 * An interface representing a product Cache.
 */
public interface ProductCache {

    Observable<ProductBean> get(final int productId);

    void put(ProductBean productEntity);

    boolean isCached(final int productId);

    boolean isExpired();

    void evictAll();
}

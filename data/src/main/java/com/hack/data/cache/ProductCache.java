/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.cache;

import com.hack.data.entity.response.ProductBean;

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

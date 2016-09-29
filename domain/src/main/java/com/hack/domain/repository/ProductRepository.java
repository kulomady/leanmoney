/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain.repository;

import com.hack.domain.ProductDomain;
import com.hack.domain.item.ProductItem;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link ProductDomain} related data.
 */
public interface ProductRepository {

    Observable<ProductDomain> searchProduct(final String queryValue, final int start,final int rows,final String device);
    Observable<ProductItem> product(final int productId);
}

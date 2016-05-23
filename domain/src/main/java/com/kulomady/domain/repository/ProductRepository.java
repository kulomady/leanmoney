package com.kulomady.domain.repository;

import com.kulomady.domain.ProductDomain;
import com.kulomady.domain.item.ProductItem;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Product} related data.
 */
public interface ProductRepository {

    Observable<ProductDomain> searchProduct(final String queryValue, final int start,final int rows,final String device);
    Observable<ProductItem> product(final int productId);
}

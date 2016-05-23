package com.kulomady.data.repository;

import com.kulomady.data.entity.mapper.ProductEntityDataMapper;
import com.kulomady.data.repository.datastore.productDataStore.ProductDataStore;
import com.kulomady.data.repository.datastore.productDataStore.ProductDataStoreFactory;
import com.kulomady.domain.ProductDomain;
import com.kulomady.domain.item.ProductItem;
import com.kulomady.domain.repository.ProductRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 *
 * Created by kulomady on 5/6/16.
 * {@link ProductRepository} for retrieving product data.
 */
@Singleton
public class ProductDataRepository implements ProductRepository {

    private final ProductDataStoreFactory productDataStoreFactory;
    private final ProductEntityDataMapper productEntityDataMapper;

    @Inject
    public ProductDataRepository(ProductDataStoreFactory dataStoreFactory,
                                 ProductEntityDataMapper productEntityDataMapper) {
        this.productDataStoreFactory = dataStoreFactory;
        this.productEntityDataMapper = productEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<ProductDomain> searchProduct(String queryValue, int start,int rows,String device) {
        //always get all products from the cloud
        final ProductDataStore productDataStore = this.productDataStoreFactory.createCloudDataStore();
        return productDataStore.productEntityList1(queryValue,start,rows,device)
                .map(dataProductEntity -> this.productEntityDataMapper.transform(dataProductEntity));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override public Observable<ProductItem> product(int productId) {
        final ProductDataStore productDataStore = this.productDataStoreFactory.create(productId);
        return productDataStore.productEntityDetails(productId)
                .map(productEntity -> this.productEntityDataMapper.transform(productEntity));
    }
}

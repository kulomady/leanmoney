package com.kulomady.data.repository.datastore.productDataStore;

import com.kulomady.data.entity.response.DataProductEntity;
import com.kulomady.data.entity.response.ProductBean;

import rx.Observable;


/**
 * Interface that represents a data store from where data is retrieved.
 * Created by kulomady on 5/6/16.
 */
public interface ProductDataStore {


    Observable<ProductBean> productEntityDetails(final int productId);

    Observable<DataProductEntity> productEntityList1(String queryValue, int start,int rows,String device);
}

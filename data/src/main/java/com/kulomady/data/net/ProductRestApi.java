package com.kulomady.data.net;


import com.kulomady.data.entity.response.DataProductEntity;
import com.kulomady.data.entity.response.ProductBean;

import rx.Observable;

/**
 * ProductRestApi for retrieving product data from the network.
 */
public interface ProductRestApi {

    Observable<ProductBean> productEntityById(final int userId);

    Observable<DataProductEntity> productEntityList1(String queryValue, int start,int rows,String device);
}

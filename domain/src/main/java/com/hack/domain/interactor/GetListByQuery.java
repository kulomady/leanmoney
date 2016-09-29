/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain.interactor;


import com.hack.domain.executor.PostExecutionThread;
import com.hack.domain.executor.ThreadExecutor;
import com.hack.domain.repository.ProductRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Product}.
 */
public class GetListByQuery extends UseCase {

    private String device;
    private int rows;
    private final ProductRepository productRepository;
    private  String queryValue;
    private  int start;

    @Inject
    public GetListByQuery(ProductRepository productRepository,
                          ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.productRepository = productRepository;

    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setQueryValue(String queryValue) {
        this.queryValue = queryValue;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.productRepository.searchProduct(queryValue,start,rows,device);
    }
}

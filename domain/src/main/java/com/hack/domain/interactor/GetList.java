/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain.interactor;


import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Product}.
 */
public class GetList extends UseCase {

    private String device;
    private int rows;
    private final com.hack.domain.repository.ProductRepository productRepository;
    private final String queryValue;
    private final int start;

    @Inject
    public GetList(String query, int start, String device, int rows, com.hack.domain.repository.ProductRepository productRepository,
                   com.hack.domain.executor.ThreadExecutor threadExecutor, com.hack.domain.executor.PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.device = device;
        this.rows = rows;
        this.productRepository = productRepository;
        this.queryValue = query;
        this.start = start;
    }


    @Override
    public Observable buildUseCaseObservable() {
        return this.productRepository.searchProduct(queryValue,start,rows,device);
    }
}

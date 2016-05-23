package com.kulomady.domain.interactor;


import com.kulomady.domain.executor.PostExecutionThread;
import com.kulomady.domain.executor.ThreadExecutor;
import com.kulomady.domain.repository.ProductRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Product}.
 */
public class GetList extends UseCase {

    private String device;
    private int rows;
    private final ProductRepository productRepository;
    private final String queryValue;
    private final int start;

    @Inject
    public GetList(String query, int start,String device,int rows, ProductRepository productRepository,
                   ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
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

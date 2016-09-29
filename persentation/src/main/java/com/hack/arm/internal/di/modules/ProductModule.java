/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.internal.di.modules;

/**
 * Dagger module that provides product related collaborators.
 * Created by kulomady on 5/6/16.
 */

import com.hack.domain.executor.ThreadExecutor;
import com.hack.domain.interactor.GetDetails;
import com.hack.domain.interactor.GetList;
import com.hack.domain.interactor.UseCase;
import com.hack.domain.repository.ProductRepository;
import com.hack.arm.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class ProductModule {
    private int productId = -1;
    private String query = "";
    private String device;
    private int start;
    private int rows;


    public ProductModule() {
    }

    public ProductModule(String query,String device, int start,int rows) {
        this.query = query;
        this.device = device;
        this.start = start;
        this.rows = rows;
    }

    public ProductModule(int productId) {
        this.productId = productId;
    }


    @Provides
    @PerActivity
    @Named("productList")
    UseCase provideGetProductListUseCase(ProductRepository productRepository, ThreadExecutor threadExecutor,
                                         com.hack.domain.executor.PostExecutionThread postExecutionThread) {
        return new GetList(
                query,
                start,
                device,
                rows,
                productRepository,
                threadExecutor,
                postExecutionThread
        );
    }

    @Provides
    @PerActivity
    @Named("productListByQuery")
    com.hack.domain.interactor.GetListByQuery provideSearchGetProductUseCase(ProductRepository productRepository, ThreadExecutor threadExecutor,
                                                                             com.hack.domain.executor.PostExecutionThread postExecutionThread) {
        return new com.hack.domain.interactor.GetListByQuery(
                productRepository,
                threadExecutor,
                postExecutionThread
        );
    }

    @Provides
    @PerActivity
    @Named("productDetails")
    UseCase provideGetProductDetailsUseCase(
            ProductRepository productRepository, ThreadExecutor threadExecutor,
            com.hack.domain.executor.PostExecutionThread postExecutionThread) {

        return new GetDetails(productId, productRepository, threadExecutor, postExecutionThread);
    }

}

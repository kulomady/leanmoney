package com.kulomady.tokopedia.internal.di.modules;

/**
 * Dagger module that provides product related collaborators.
 * Created by kulomady on 5/6/16.
 */

import com.kulomady.domain.executor.PostExecutionThread;
import com.kulomady.domain.executor.ThreadExecutor;
import com.kulomady.domain.interactor.GetDetails;
import com.kulomady.domain.interactor.GetList;
import com.kulomady.domain.interactor.GetListByQuery;
import com.kulomady.domain.interactor.UseCase;
import com.kulomady.domain.repository.ProductRepository;
import com.kulomady.tokopedia.internal.di.PerActivity;

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
                                         PostExecutionThread postExecutionThread) {
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
    GetListByQuery provideSearchGetProductUseCase(ProductRepository productRepository, ThreadExecutor threadExecutor,
                                                  PostExecutionThread postExecutionThread) {
        return new GetListByQuery(
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
            PostExecutionThread postExecutionThread) {

        return new GetDetails(productId, productRepository, threadExecutor, postExecutionThread);
    }

}

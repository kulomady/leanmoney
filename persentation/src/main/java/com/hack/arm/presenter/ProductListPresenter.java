
/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.presenter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.hack.arm.exception.ErrorMessageFactory;
import com.hack.arm.mapper.ProductModelDataMapper;
import com.hack.arm.model.ProductModel;
import com.hack.arm.view.ProductListView;
import com.hack.domain.ProductDomain;
import com.hack.arm.internal.di.PerActivity;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class ProductListPresenter implements Presenter {

    private static final String TAG = "ProductListPresenter";

    private ProductListView viewListView;

    private final com.hack.domain.interactor.UseCase getProductListUseCase;
    private com.hack.domain.interactor.GetListByQuery getProductListByQueryUseCase;
    private final ProductModelDataMapper productModelDataMapper;

    private String pagingUrl;

    @Inject
    public ProductListPresenter(@Named("productList") com.hack.domain.interactor.UseCase getProductListUseCase,
                                @Named("productListByQuery") com.hack.domain.interactor.GetListByQuery getProductListByQueryUseCase,
                                ProductModelDataMapper productModelDataMapper) {
        this.getProductListUseCase = getProductListUseCase;
        this.productModelDataMapper = productModelDataMapper;
        this.getProductListByQueryUseCase = getProductListByQueryUseCase;
    }

    public void setView(@NonNull ProductListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getProductListUseCase.unsubscribe();
        this.viewListView = null;
    }

    public void initialize() {
        this.loadProductList();
    }

    public void loadMoreProduct() {
        if (pagingUrl != null) {
            this.hideViewRetry();
            this.showLoadMoreLoading();
            this.loadmoreProducts();
        }
    }

    private void loadProductList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getProductList();
    }

    public void onProductClicked(ProductModel productModel) {
        this.viewListView.viewProduct(productModel);
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void showLoadMoreLoading() {
        this.viewListView.showLoadingLoadmoreProduct();
    }

    private void hideLoadmoreLoading() {
        this.viewListView.hideLoadingLoadmoreProduct();
    }


    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(com.hack.domain.exception.ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showProductsCollectionInView(com.hack.domain.ProductDomain productDomain) {
        pagingUrl = productDomain.getResult().getPaging().getUri_next();
        Log.d(TAG, "showProductsCollectionInView: pagging url " + pagingUrl);

        final Collection<ProductModel> productModelsCollection =
                this.productModelDataMapper.transform(productDomain);
        this.viewListView.renderProductList(productModelsCollection);
    }

    private void showMoreProductsCollectionInView(com.hack.domain.ProductDomain productDomain) {
        pagingUrl = productDomain.getResult().getPaging().getUri_next();
        Log.d(TAG, "showProductsCollectionInView: pagging url " + pagingUrl);

        final Collection<ProductModel> productModelsCollection =
                this.productModelDataMapper.transform(productDomain);
        this.hideLoadmoreLoading();
        this.viewListView.renderMoreProductList(productModelsCollection);
        this.viewListView.setMoreLoading(false);
    }


    private void loadmoreProducts() {
        if(pagingUrl !=null) {
            String device = getParamValueFromUrlString("device");
            String query = getParamValueFromUrlString("q");
            int rows = Integer.parseInt(getParamValueFromUrlString("rows"));
            int start = Integer.parseInt(getParamValueFromUrlString("start"));

            this.getProductListByQueryUseCase.setDevice(device);
            this.getProductListByQueryUseCase.setQueryValue(query);
            this.getProductListByQueryUseCase.setStart(start);
            this.getProductListByQueryUseCase.setRows(rows);
            this.getProductListByQueryUseCase.execute(new MoreProductSubscriber());
        }
    }

    private void getProductList() {
        this.getProductListUseCase.execute(new ProductListSubscriber());
    }

    private String getParamValueFromUrlString(String paramsName) {
        Uri uri = Uri.parse(pagingUrl);
        return uri.getQueryParameter(paramsName);
    }

    private final class ProductListSubscriber extends com.hack.domain.interactor.DefaultSubscriber<ProductDomain> {

        @Override
        public void onCompleted() {
            ProductListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            ProductListPresenter.this.hideViewLoading();
            ProductListPresenter.this.showErrorMessage(new com.hack.domain.exception.DefaultErrorBundle((Exception) e));
            ProductListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(com.hack.domain.ProductDomain productDomain) {
            Log.d(TAG, "onNext: " + new Gson().toJson(productDomain));
            ProductListPresenter.this.showProductsCollectionInView(productDomain);
        }
    }

    private final class MoreProductSubscriber extends com.hack.domain.interactor.DefaultSubscriber<ProductDomain> {

        @Override
        public void onCompleted() {
            ProductListPresenter.this.hideLoadmoreLoading();
        }

        @Override
        public void onError(Throwable e) {
            ProductListPresenter.this.hideLoadmoreLoading();
            ProductListPresenter.this.showErrorMessage(new com.hack.domain.exception.DefaultErrorBundle((Exception) e));
            ProductListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(com.hack.domain.ProductDomain productDomain) {
            Log.d(TAG, "onNext load more: " + new Gson().toJson(productDomain));
            ProductListPresenter.this.showMoreProductsCollectionInView(productDomain);
        }
    }


}

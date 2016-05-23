
package com.kulomady.tokopedia.presenter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.kulomady.domain.ProductDomain;
import com.kulomady.domain.exception.DefaultErrorBundle;
import com.kulomady.domain.exception.ErrorBundle;
import com.kulomady.domain.interactor.DefaultSubscriber;
import com.kulomady.domain.interactor.GetListByQuery;
import com.kulomady.domain.interactor.UseCase;
import com.kulomady.tokopedia.exception.ErrorMessageFactory;
import com.kulomady.tokopedia.internal.di.PerActivity;
import com.kulomady.tokopedia.mapper.ProductModelDataMapper;
import com.kulomady.tokopedia.model.ProductModel;
import com.kulomady.tokopedia.view.ProductListView;

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

    private final UseCase getProductListUseCase;
    private GetListByQuery getProductListByQueryUseCase;
    private final ProductModelDataMapper productModelDataMapper;

    private String pagingUrl;

    @Inject
    public ProductListPresenter(@Named("productList") UseCase getProductListUseCase,
                                @Named("productListByQuery") GetListByQuery getProductListByQueryUseCase,
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

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showProductsCollectionInView(ProductDomain productDomain) {
        pagingUrl = productDomain.getResult().getPaging().getUri_next();
        Log.d(TAG, "showProductsCollectionInView: pagging url " + pagingUrl);

        final Collection<ProductModel> productModelsCollection =
                this.productModelDataMapper.transform(productDomain);
        this.viewListView.renderProductList(productModelsCollection);
    }

    private void showMoreProductsCollectionInView(ProductDomain productDomain) {
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

    private final class ProductListSubscriber extends DefaultSubscriber<ProductDomain> {

        @Override
        public void onCompleted() {
            ProductListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            ProductListPresenter.this.hideViewLoading();
            ProductListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            ProductListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(ProductDomain productDomain) {
            Log.d(TAG, "onNext: " + new Gson().toJson(productDomain));
            ProductListPresenter.this.showProductsCollectionInView(productDomain);
        }
    }

    private final class MoreProductSubscriber extends DefaultSubscriber<ProductDomain> {

        @Override
        public void onCompleted() {
            ProductListPresenter.this.hideLoadmoreLoading();
        }

        @Override
        public void onError(Throwable e) {
            ProductListPresenter.this.hideLoadmoreLoading();
            ProductListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            ProductListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(ProductDomain productDomain) {
            Log.d(TAG, "onNext load more: " + new Gson().toJson(productDomain));
            ProductListPresenter.this.showMoreProductsCollectionInView(productDomain);
        }
    }


}

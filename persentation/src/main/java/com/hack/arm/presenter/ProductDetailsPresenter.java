/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.hack.arm.exception.ErrorMessageFactory;
import com.hack.arm.mapper.ProductModelDataMapper;
import com.hack.arm.model.ProductModel;
import com.hack.arm.view.ProductDetailsView;
import com.hack.domain.item.ProductItem;
import com.hack.arm.internal.di.PerActivity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class ProductDetailsPresenter implements Presenter {
  private static final String TAG = "ProductDetailsPresenter";
  private ProductDetailsView viewDetailsView;

  private final com.hack.domain.interactor.UseCase getUserDetailsUseCase;
  private final ProductModelDataMapper productModelDataMapper;

  @Inject
  public ProductDetailsPresenter(@Named("productDetails") com.hack.domain.interactor.UseCase getUserDetailsUseCase,
                                 ProductModelDataMapper productModelDataMapper) {
    this.getUserDetailsUseCase = getUserDetailsUseCase;
    this.productModelDataMapper = productModelDataMapper;
  }

  public void setView(@NonNull ProductDetailsView view) {
    this.viewDetailsView = view;
  }

  @Override
  public void resume() {}

  @Override
  public void pause() {}

  @Override
  public void destroy() {
    this.getUserDetailsUseCase.unsubscribe();
    this.viewDetailsView = null;
  }

  /**
   * Initializes the presenter by start retrieving product details.
   */
  public void initialize() {
    Log.d(TAG, "initialize called ");
    this.loadUserDetails();
  }

  /**
   * Loads product details.
   */
  private void loadUserDetails() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getUserDetails();
  }

  private void showViewLoading() {
    this.viewDetailsView.showLoading();
  }

  private void hideViewLoading() {
    this.viewDetailsView.hideLoading();
  }

  private void showViewRetry() {
    this.viewDetailsView.showRetry();
  }

  private void hideViewRetry() {
    this.viewDetailsView.hideRetry();
  }

  private void showErrorMessage(com.hack.domain.exception.ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(),
        errorBundle.getException());
    this.viewDetailsView.showError(errorMessage);
  }

  private void showUserDetailsInView(ProductItem product) {
    final ProductModel productModel = this.productModelDataMapper.transform(product);
    this.viewDetailsView.renderUser(productModel);
  }

  private void getUserDetails() {
    this.getUserDetailsUseCase.execute(new UserDetailsSubscriber());
  }

  @RxLogSubscriber
  private final class UserDetailsSubscriber extends com.hack.domain.interactor.DefaultSubscriber<ProductItem> {

    @Override
    public void onCompleted() {
      ProductDetailsPresenter.this.hideViewLoading();
    }

    @Override
    public void onError(Throwable e) {
      ProductDetailsPresenter.this.hideViewLoading();
      ProductDetailsPresenter.this.showErrorMessage(new com.hack.domain.exception.DefaultErrorBundle((Exception) e));
      ProductDetailsPresenter.this.showViewRetry();
    }

    @Override
    public void onNext(ProductItem product) {
      ProductDetailsPresenter.this.showUserDetailsInView(product);
    }
  }
}

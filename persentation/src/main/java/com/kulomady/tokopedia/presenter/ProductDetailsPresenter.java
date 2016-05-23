package com.kulomady.tokopedia.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.kulomady.domain.exception.DefaultErrorBundle;
import com.kulomady.domain.exception.ErrorBundle;
import com.kulomady.domain.interactor.DefaultSubscriber;
import com.kulomady.domain.interactor.UseCase;
import com.kulomady.domain.item.ProductItem;
import com.kulomady.tokopedia.exception.ErrorMessageFactory;
import com.kulomady.tokopedia.internal.di.PerActivity;
import com.kulomady.tokopedia.mapper.ProductModelDataMapper;
import com.kulomady.tokopedia.model.ProductModel;
import com.kulomady.tokopedia.view.ProductDetailsView;

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

  private final UseCase getUserDetailsUseCase;
  private final ProductModelDataMapper productModelDataMapper;

  @Inject
  public ProductDetailsPresenter(@Named("productDetails") UseCase getUserDetailsUseCase,
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

  private void showErrorMessage(ErrorBundle errorBundle) {
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
  private final class UserDetailsSubscriber extends DefaultSubscriber<ProductItem> {

    @Override
    public void onCompleted() {
      ProductDetailsPresenter.this.hideViewLoading();
    }

    @Override
    public void onError(Throwable e) {
      ProductDetailsPresenter.this.hideViewLoading();
      ProductDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      ProductDetailsPresenter.this.showViewRetry();
    }

    @Override
    public void onNext(ProductItem product) {
      ProductDetailsPresenter.this.showUserDetailsInView(product);
    }
  }
}

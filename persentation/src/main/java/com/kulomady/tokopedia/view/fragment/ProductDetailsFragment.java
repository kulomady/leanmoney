package com.kulomady.tokopedia.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kulomady.tokopedia.R;
import com.kulomady.tokopedia.internal.di.components.ProductComponent;
import com.kulomady.tokopedia.model.ProductModel;
import com.kulomady.tokopedia.presenter.ProductDetailsPresenter;
import com.kulomady.tokopedia.view.ProductDetailsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductDetailsFragment extends BaseFragment implements ProductDetailsView {



    @Inject
    ProductDetailsPresenter productDetailsPresenter;

    private Unbinder unbinder;

    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tv_fullname)
    TextView tv_fullname;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_followers)
    TextView tv_followers;
    @BindView(R.id.tv_description)
    TextView tv_description;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rl_retry)
    RelativeLayout rl_retry;
    @BindView(R.id.bt_retry)
    Button bt_retry;

    private UserDetailCallback callback;

    public ProductDetailsFragment() {
        setRetainInstance(true);
    }

    public ProductDetailsFragment newInstance(){
        return new ProductDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ProductComponent.class).inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        unbinder= ButterKnife.bind(this, fragmentView);

        collapsingToolbarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        return fragmentView;
    }


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof UserDetailCallback) {
            this.callback = (UserDetailCallback) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.callback =null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.productDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.productDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.productDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.productDetailsPresenter.destroy();
    }

    @Override
    public void renderUser(ProductModel user) {
        if (user != null) {
            getActivity().getActionBar().setTitle(user.getShop_name());
            collapsingToolbarLayout.setTitle(user.getShop_name());
            callback.loadImageFromUrl(user.getProduct_image());
            this.tv_fullname.setText(user.getShop_location());
        }
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    /**
     * Loads all products.
     */
    private void loadUserDetails() {
        if (this.productDetailsPresenter != null) {
            this.productDetailsPresenter.initialize();
        }
    }



    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        ProductDetailsFragment.this.loadUserDetails();
    }

    public interface UserDetailCallback{
        void loadImageFromUrl(String url);
    }
}

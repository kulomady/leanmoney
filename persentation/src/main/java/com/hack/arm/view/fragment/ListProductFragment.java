/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.hack.arm.view.ProductListView;
import com.hack.arm.view.viewComponent.ProductsLayoutManager;
import com.hack.arm.R;
import com.hack.arm.internal.di.components.ProductComponent;
import com.hack.arm.model.ProductModel;
import com.hack.arm.presenter.ProductListPresenter;
import com.hack.arm.view.adapter.ProductsAdapter;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
public class ListProductFragment extends  BaseFragment implements
        ProductListView,
        ProductsAdapter.OnLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ListProductFragment";

    public interface ProductListListener {
        void onProductClicked(final ProductModel productModel);
    }

    @Inject
    ProductListPresenter productListPresenter;

    private ProductsAdapter productsAdapter;

    @BindView(R.id.rv_products) RecyclerView rv_products;
    @BindView(R.id.swipeRefresh) SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.rl_progress) RelativeLayout rl_progress;
    @BindView(R.id.rl_retry) RelativeLayout rl_retry;
    @BindView(R.id.bt_retry) Button bt_retry;

    private Unbinder unbinder;

    private ProductListListener productListListener;

    public ListProductFragment() {
        setRetainInstance(true);
    }

    private ProductsAdapter.OnItemClickListener onItemClickListener =
            new ProductsAdapter.OnItemClickListener() {
                @Override public void onProductItemClicked(ProductModel productModel) {
                    if (ListProductFragment.this.productListPresenter != null && productModel != null) {
                        ListProductFragment.this.productListPresenter.onProductClicked(productModel);
                    }
                }
            };

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(ProductComponent.class).inject(this);
    }

    @SuppressWarnings("deprecation")
    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ProductListListener) {
            this.productListListener = (ProductListListener) activity;
        }
    }


    @Override public void onDetach() {
        super.onDetach();
        this.productListListener = null;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_list_product, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.productListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadProductList();
        }
    }

    @Override public void onResume() {
        super.onResume();
        this.productListPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.productListPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        rv_products.setAdapter(null);
        unbinder.unbind();

    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.productListPresenter.destroy();
    }

    @Override public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override public void renderProductList(Collection<ProductModel> productModelCollection) {
        if (productModelCollection != null) {
            Log.d(TAG, "renderProductList: " + new Gson().toJson(productModelCollection));
            this.productsAdapter.setProductsCollection(productModelCollection);
        }
    }

    @Override
    public void renderMoreProductList(Collection<ProductModel> productModelCollection) {
        Log.d(TAG, "renderMoreProductList() called with: " + "productModelCollection = [" + productModelCollection + "]");
        productsAdapter.addItemsMore((List<ProductModel>) productModelCollection);
    }

    @Override
    public void viewProduct(ProductModel productModel) {
        if (this.productListListener != null) {
            this.productListListener.onProductClicked(productModel);
        }
    }

    @Override
    public void showLoadingLoadmoreProduct() {
        productsAdapter.setProgressMore(true);
    }

    @Override
    public void hideLoadingLoadmoreProduct() {
        productsAdapter.setProgressMore(false);
    }

    @Override
    public void setMoreLoading(boolean isMoreLoading) {
        productsAdapter.setMoreLoading(isMoreLoading);
    }


    @Override public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {

        this.productsAdapter = new ProductsAdapter(this);
        ProductsLayoutManager productsLayoutManager = new ProductsLayoutManager(context(), 2);

        productsLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(productsAdapter.VIEW_ITEM == productsAdapter.getItemViewType(position)){
                    return 1;
                }else{
                    return 2;
                }
            }
        });
        this.productsAdapter.setLayoutManager(productsLayoutManager);
        this.productsAdapter.setRecyclerView(this.rv_products);
        this.rv_products.setAdapter(productsAdapter);
        this.rv_products.setLayoutManager(productsLayoutManager);

        this.swipeRefresh.setOnRefreshListener(this);
        this.productsAdapter.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void onLoadMore() {
        this.productListPresenter.loadMoreProduct();

//        this.showLoadingLoadmoreProduct();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                productsAdapter.setProgressMore(false);
//                hideLoadingLoadmoreProduct();
//                int start = productsAdapter.getItemCount();
//                int end = start + 20;
//                List<ProductModel> itemList = new ArrayList<>();
//                for (int i = start + 1; i <= end; i++) {
//                    itemList.add(new ProductModel());
//                }
//                hideLoadingLoadmoreProduct();
//                productsAdapter.addItemsMore(itemList);
//                setMoreLoading(false);
////                hideLoadingLoadmoreProduct();
//            }
//        },2000);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);
                loadProductList();

            }
        },2000);
    }

    private void loadProductList() {
        this.productListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry) void onButtonRetryClick() {
        ListProductFragment.this.loadProductList();
    }



}
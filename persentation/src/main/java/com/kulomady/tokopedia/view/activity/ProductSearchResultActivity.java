package com.kulomady.tokopedia.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.kulomady.tokopedia.R;
import com.kulomady.tokopedia.internal.di.HasComponent;
import com.kulomady.tokopedia.internal.di.components.DaggerProductComponent;
import com.kulomady.tokopedia.internal.di.components.ProductComponent;
import com.kulomady.tokopedia.internal.di.modules.ProductModule;
import com.kulomady.tokopedia.model.ProductModel;
import com.kulomady.tokopedia.view.fragment.ListProductFragment;
import com.kulomady.tokopedia.view.utils.ViewUtils;

import butterknife.BindView;

public class ProductSearchResultActivity extends BaseDrawerActivity
        implements HasComponent<ProductComponent>,ListProductFragment.ProductListListener {

    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";
    private static final String TAG = "ProductResultActivity";

    @BindView(R.id.fragmentContainer)
    FrameLayout contentRoot;

    private int drawingStartLocation;
    private ProductComponent productComponent;

    private String query;

    public static Intent getCallingIntent(Context targetContext, String query) {
        Intent callingIntent = new Intent(targetContext, ProductSearchResultActivity.class);
        callingIntent.putExtra(SearchManager.QUERY, query);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        initializeActivity(savedInstanceState);
        this.initializeInjector();
        Log.d(TAG, "onCreate: "+ new Gson().toJson(productComponent));

    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.query = getIntent().getStringExtra(SearchManager.QUERY);
            addFragment(R.id.fragmentContainer, new ListProductFragment());
            drawingStartLocation = getIntent().getIntExtra(ARG_DRAWING_START_LOCATION, 0);
            contentRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    contentRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                    startIntroAnimation();
                    return true;
                }
            });
        }
        else{
            this.query = savedInstanceState.getString(SearchManager.QUERY);
            drawingStartLocation = 0;
        }
    }

    private void initializeInjector() {
        int start = 0;
        int rows = 10;
        String device = "android";

        this.productComponent = DaggerProductComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .productModule(new ProductModule(query,device,start,rows))
                .build();
    }

    private void startIntroAnimation() {
        ViewCompat.setElevation(getToolbar(), 0);
        contentRoot.setScaleY(0.1f);
        contentRoot.setPivotY(drawingStartLocation);
        contentRoot.animate()
                .scaleY(1)
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewCompat.setElevation(getToolbar(), ViewUtils.dpToPx(8));
                    }
                })
                .start();
    }

    @Override
    public void onBackPressed() {
        ViewCompat.setElevation(getToolbar(), 0);
        contentRoot.animate()
                .translationY(ViewUtils.getScreenHeight(this))
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ProductSearchResultActivity.super.onBackPressed();
                        overridePendingTransition(0, 0);
                    }
                })
                .start();
    }

    @Override
    public ProductComponent getComponent() {
        return productComponent;
    }

    @Override
    public void onProductClicked(ProductModel productModel) {
        this.navigator.navigateToProductDetail(this, productModel.getProduct_id());
        overridePendingTransition(0, 0);
    }


}

package com.kulomady.tokopedia.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.kulomady.tokopedia.R;
import com.kulomady.tokopedia.internal.di.HasComponent;
import com.kulomady.tokopedia.internal.di.components.DaggerProductComponent;
import com.kulomady.tokopedia.internal.di.components.ProductComponent;
import com.kulomady.tokopedia.view.fragment.ListProductFragment;
import com.kulomady.tokopedia.view.utils.ViewUtils;

import butterknife.BindView;


public class ListProductActivity extends BaseDrawerActivity implements
        HasComponent<ProductComponent> {


    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";
    private static final String TAG = "ListProductActivity";
    @BindView(R.id.fragmentContainer)
    FrameLayout contentRoot;

    private int drawingStartLocation;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ListProductActivity.class);
    }

    private ProductComponent productComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_list_product);


        this.initializeInjector();
        Log.d(TAG, "onCreate: "+ new Gson().toJson(productComponent));
        drawingStartLocation = getIntent().getIntExtra(ARG_DRAWING_START_LOCATION, 0);
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new ListProductFragment());
            contentRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    contentRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                    startIntroAnimation();
                    return true;
                }
            });
        }
    }

    private void initializeInjector() {
        this.productComponent = DaggerProductComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
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
                        ListProductActivity.super.onBackPressed();
                        overridePendingTransition(0, 0);
                    }
                })
                .start();
    }

    @Override
    public ProductComponent getComponent() {
        return productComponent;
    }

//    @Override
//    public void onProductClicked(ProductModel productModel) {
//        this.navigator.navigateToUserDetail(this, productModel.getUserId());
//        overridePendingTransition(0, 0);
//    }
}

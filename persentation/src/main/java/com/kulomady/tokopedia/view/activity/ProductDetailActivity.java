package com.kulomady.tokopedia.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.kulomady.tokopedia.R;
import com.kulomady.tokopedia.internal.di.HasComponent;
import com.kulomady.tokopedia.internal.di.components.DaggerProductComponent;
import com.kulomady.tokopedia.internal.di.components.ProductComponent;
import com.kulomady.tokopedia.internal.di.modules.ProductModule;
import com.kulomady.tokopedia.presenter.ProductDetailsPresenter;
import com.kulomady.tokopedia.view.fragment.ProductDetailsFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;

public class ProductDetailActivity extends BaseActivity implements
        HasComponent<ProductComponent>,
        ProductDetailsFragment.UserDetailCallback {

    private static final String TAG = "ProductDetailActivity";
    private static final String INTENT_EXTRA_PARAM_USER_ID = "com.kulomady.INTENT_PARAM_USER_ID";
    private static final String INSTANCE_STATE_PARAM_USER_ID = "com.kulomady.STATE_PARAM_USER_ID";

    public static Intent getCallingIntent(Context context, int userId) {
        Intent callingIntent = new Intent(context, ProductDetailActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
        return callingIntent;
    }

    @Inject
    ProductDetailsPresenter productDetailsPresenter;
    @BindView(R.id.iv_cover)
    ImageView iv_cover;

    private int userId;
    private ProductComponent productComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_product_detail);
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();

    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.userId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
            addFragment(R.id.fragmentContainer, new ProductDetailsFragment());
        } else {
            this.userId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
        }
    }

    private void initializeInjector() {
        this.productComponent = DaggerProductComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .productModule(new ProductModule(userId))
                .build();

    }



    @Override public ProductComponent getComponent() {
        return productComponent;
    }

    @Override
    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            if(getSupportActionBar()!=null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadImageFromUrl(String url) {
        Picasso.with(this)
                    .load(url)
                    .resize(500, 350)
                    .centerCrop()
                    .into(iv_cover);
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        this.finish();


    }
}




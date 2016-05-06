package com.kulomady.mycleanarchitecture.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kulomady.mycleanarchitecture.R;
import com.kulomady.mycleanarchitecture.internal.di.HasComponent;
import com.kulomady.mycleanarchitecture.internal.di.components.DaggerUserComponent;
import com.kulomady.mycleanarchitecture.internal.di.components.UserComponent;
import com.kulomady.mycleanarchitecture.internal.di.modules.UserModule;
import com.kulomady.mycleanarchitecture.view.fragment.UserDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailActivity extends BaseActivity implements HasComponent<UserComponent>,UserDetailsFragment.UserDetailCallback {
    private static final String INTENT_EXTRA_PARAM_USER_ID = "com.kulomady.INTENT_PARAM_USER_ID";
    private static final String INSTANCE_STATE_PARAM_USER_ID = "com.kulomady.STATE_PARAM_USER_ID";

    public static Intent getCallingIntent(Context context, int userId) {
        Intent callingIntent = new Intent(context, UserDetailActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId);
        return callingIntent;
    }
    @BindView(R.id.iv_cover)
    ImageView iv_cover;
    private int userId;
    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_user_detail);
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
        ButterKnife.bind(this);
        setupToolbar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.userId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
            addFragment(R.id.fragmentContainer, new UserDetailsFragment());
        } else {
            this.userId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule(this.userId))
                .build();
    }

    @Override public UserComponent getComponent() {
        return userComponent;
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void loadImageFromUrl(String url) {
        Glide.with(this)
                    .load(url)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(500,350)
                    .into(iv_cover);
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}




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

import com.kulomady.mycleanarchitecture.R;
import com.kulomady.mycleanarchitecture.internal.di.HasComponent;
import com.kulomady.mycleanarchitecture.internal.di.components.DaggerUserComponent;
import com.kulomady.mycleanarchitecture.internal.di.components.UserComponent;
import com.kulomady.mycleanarchitecture.model.UserModel;
import com.kulomady.mycleanarchitecture.view.fragment.ListUserFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ListUserActivity extends BaseActivity implements
        HasComponent<UserComponent>,ListUserFragment.UserListListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ListUserActivity.class);
    }

    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_list_user);
        setupToolbar();
        ButterKnife.bind(this);
        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new ListUserFragment());
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.fab)
    void navigateToDetail() {
        this.navigator.navigateToUserDetail(this, 1);
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    @Override
    public void onUserClicked(UserModel userModel) {
        this.navigator.navigateToUserDetail(this, userModel.getUserId());
    }
}

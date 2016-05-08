package com.kulomady.mycleanarchitecture.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.kulomady.mycleanarchitecture.R;
import com.kulomady.mycleanarchitecture.internal.di.HasComponent;
import com.kulomady.mycleanarchitecture.internal.di.components.DaggerUserComponent;
import com.kulomady.mycleanarchitecture.internal.di.components.UserComponent;
import com.kulomady.mycleanarchitecture.model.UserModel;
import com.kulomady.mycleanarchitecture.view.fragment.ListUserFragment;
import com.kulomady.mycleanarchitecture.view.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ListUserActivity extends BaseDrawerActivity implements
        HasComponent<UserComponent>,ListUserFragment.UserListListener {
    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";
    private static final String TAG = "ListUserActivity";
    @BindView(R.id.fragmentContainer)
    FrameLayout contentRoot;

    private int drawingStartLocation;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ListUserActivity.class);
    }

    private UserComponent userComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_list_user);


        this.initializeInjector();
        Log.d(TAG, "onCreate: "+ new Gson().toJson(userComponent));
        drawingStartLocation = getIntent().getIntExtra(ARG_DRAWING_START_LOCATION, 0);
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new ListUserFragment());
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
        this.userComponent = DaggerUserComponent.builder()
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
                        ListUserActivity.super.onBackPressed();
                        overridePendingTransition(0, 0);
                    }
                })
                .start();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    @Override
    public void onUserClicked(UserModel userModel) {
        this.navigator.navigateToUserDetail(this, userModel.getUserId());
        overridePendingTransition(0, 0);
    }
}

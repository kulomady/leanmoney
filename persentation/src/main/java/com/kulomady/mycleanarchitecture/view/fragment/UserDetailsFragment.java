package com.kulomady.mycleanarchitecture.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kulomady.mycleanarchitecture.R;
import com.kulomady.mycleanarchitecture.internal.di.components.UserComponent;
import com.kulomady.mycleanarchitecture.model.UserModel;
import com.kulomady.mycleanarchitecture.presenter.UserDetailsPresenter;
import com.kulomady.mycleanarchitecture.view.UserDetailsView;
import com.kulomady.mycleanarchitecture.view.component.AutoLoadImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserDetailsFragment extends BaseFragment implements UserDetailsView {



    @Inject
    UserDetailsPresenter userDetailsPresenter;

    private Unbinder unbinder;

//    ImageView iv_cover;

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

    public UserDetailsFragment() {
        setRetainInstance(true);
    }

    public UserDetailsFragment newInstance(){
        return new UserDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(UserComponent.class).inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_detail, container, false);
        unbinder= ButterKnife.bind(this, fragmentView);

//        iv_cover = (AutoLoadImageView) getActivity().findViewById(R.id.iv_cover);
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
        this.userDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserDetails();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.userDetailsPresenter.destroy();
    }

    @Override
    public void renderUser(UserModel user) {
        if (user != null) {
            collapsingToolbarLayout.setTitle(user.getFullName());
            callback.loadImageFromUrl(user.getCoverUrl());
            this.tv_fullname.setText(user.getFullName());
            this.tv_email.setText(user.getEmail());
            this.tv_followers.setText(String.valueOf(user.getFollowers()));
            this.tv_description.setText(user.getDescription());
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
     * Loads all users.
     */
    private void loadUserDetails() {
        if (this.userDetailsPresenter != null) {
            this.userDetailsPresenter.initialize();
        }
    }



    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserDetailsFragment.this.loadUserDetails();
    }

    public interface UserDetailCallback{
        void loadImageFromUrl(String url);
    }
}

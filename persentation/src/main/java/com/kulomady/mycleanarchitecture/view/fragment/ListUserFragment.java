package com.kulomady.mycleanarchitecture.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.kulomady.mycleanarchitecture.R;
import com.kulomady.mycleanarchitecture.internal.di.components.UserComponent;
import com.kulomady.mycleanarchitecture.model.UserModel;
import com.kulomady.mycleanarchitecture.presenter.UserListPresenter;
import com.kulomady.mycleanarchitecture.view.UserListView;
import com.kulomady.mycleanarchitecture.view.adapter.UsersAdapter;
import com.kulomady.mycleanarchitecture.view.adapter.UsersLayoutManager;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListUserFragment extends  BaseFragment implements UserListView {
    /**
     * Interface for listening user list events.
     */
    public interface UserListListener {
        void onUserClicked(final UserModel userModel);
    }

    @Inject
    UserListPresenter userListPresenter;
    @Inject
    UsersAdapter usersAdapter;

    @BindView(R.id.rv_users)
    RecyclerView rv_users;
    @BindView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @BindView(R.id.rl_retry) RelativeLayout rl_retry;
    @BindView(R.id.bt_retry)
    Button bt_retry;


    private Unbinder unbinder;
    private UserListListener userListListener;

    public ListUserFragment() {
        setRetainInstance(true);
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof UserListListener) {
            this.userListListener = (UserListListener) activity;
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(UserComponent.class).inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_list_user, container, false);
        unbinder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserList();
        }
    }

    @Override public void onResume() {
        super.onResume();
        this.userListPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.userListPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        rv_users.setAdapter(null);
        unbinder.unbind();

    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.userListPresenter.destroy();
    }

    @Override public void onDetach() {
        super.onDetach();
        this.userListListener = null;
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

    @Override public void renderUserList(Collection<UserModel> userModelCollection) {
        if (userModelCollection != null) {
            this.usersAdapter.setUsersCollection(userModelCollection);
        }
    }

    @Override public void viewUser(UserModel userModel) {
        if (this.userListListener != null) {
            this.userListListener.onUserClicked(userModel);
        }
    }

    @Override public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.usersAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_users.setLayoutManager(new UsersLayoutManager(context()));
        this.rv_users.setAdapter(usersAdapter);
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.userListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry) void onButtonRetryClick() {
        ListUserFragment.this.loadUserList();
    }

    private UsersAdapter.OnItemClickListener onItemClickListener =
            new UsersAdapter.OnItemClickListener() {
                @Override public void onUserItemClicked(UserModel userModel) {
                    if (ListUserFragment.this.userListPresenter != null && userModel != null) {
                        ListUserFragment.this.userListPresenter.onUserClicked(userModel);
                    }
                }
            };
}

package com.kulomady.tokopedia.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.kulomady.tokopedia.AndroidApplication;
import com.kulomady.tokopedia.Navigation.Navigator;
import com.kulomady.tokopedia.R;
import com.kulomady.tokopedia.internal.di.components.ApplicationComponent;
import com.kulomady.tokopedia.internal.di.modules.ActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by kulomady on 5/6/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.topedLogo)
    TextView tokopediaLogoTextView;

    private MenuItem cartMenuItem;
    private MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    protected void bindViews() {
        ButterKnife.bind(this);
        setupToolbar();
    }

    public void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }

    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setCartMenuItem(menu);
        setSearchMenuItem(menu);
        initActivityForSupportSearchable(menu);
        return true;
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {

        return ((AndroidApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }


    private void setSearchMenuItem(Menu menu) {
        searchMenuItem = menu.findItem(R.id.menu_search);

    }

    private void setCartMenuItem(Menu menu) {
        cartMenuItem = menu.findItem(R.id.menu_cart);
        cartMenuItem.setActionView(R.layout.menu_item_cart_view);
    }

    private void initActivityForSupportSearchable(Menu menu) {
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
    }


    @Nullable
    public Toolbar getToolbar() {
        return toolbar;
    }

    public MenuItem getCartMenuItem() {
        return cartMenuItem;
    }

    public MenuItem getSearchMenuItem() {
        return searchMenuItem;
    }

    @Nullable
    public TextView getTokopediaLogoTextView() {
        return tokopediaLogoTextView;
    }


}

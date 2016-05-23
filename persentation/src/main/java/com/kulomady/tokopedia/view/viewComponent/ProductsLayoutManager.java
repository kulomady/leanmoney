package com.kulomady.tokopedia.view.viewComponent;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Layout manager to position items inside a {@link android.support.v7.widget.RecyclerView}.
 */
public class ProductsLayoutManager extends GridLayoutManager {


    public ProductsLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        return 300;
    }


}

/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.kulomady.mycleanarchitecture.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Layout manager to position items inside a {@link android.support.v7.widget.RecyclerView}.
 */
public class UsersLayoutManager extends LinearLayoutManager {
    public UsersLayoutManager(Context context) {
        super(context);
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        return 300;
    }
}

/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.Navigation;

import android.content.Context;
import android.content.Intent;

import com.hack.arm.view.activity.ProductDetailActivity;
import com.hack.arm.view.activity.ProductSearchResultActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * Class used to navigate through the application.
 * Created by kulomady on 5/6/16.
 */

@Singleton
public class Navigator {

    @Inject
    public Navigator() {}

    public void navigateToProductDetail(Context context, int productId) {
        if (context != null) {
            Intent intent = ProductDetailActivity.getCallingIntent(context, productId);
            context.startActivity(intent);
        }
    }

    public void navigateToProductResult(Context context, String query) {
        if (context != null) {
            Intent intent = ProductSearchResultActivity.getCallingIntent(context, query);
            context.startActivity(intent);
        }
    }
}

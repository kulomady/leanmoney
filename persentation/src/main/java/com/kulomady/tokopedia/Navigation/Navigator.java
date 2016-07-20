package com.kulomady.tokopedia.Navigation;

import android.content.Context;
import android.content.Intent;

import com.kulomady.tokopedia.view.activity.ProductDetailActivity;

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
}

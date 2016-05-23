package com.kulomady.tokopedia.Navigation;

import android.content.Context;
import android.content.Intent;

import com.kulomady.tokopedia.view.activity.ListProductActivity;
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

    /**
     * Goes to the product list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToUserList(Context context) {
        if (context != null) {
            Intent intentToLaunch = ListProductActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToUserDetail(Context context,int userId) {
        if (context != null) {
            Intent intent = ProductDetailActivity.getCallingIntent(context, userId);
            context.startActivity(intent);
        }
    }
}

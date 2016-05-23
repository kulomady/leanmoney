package com.kulomady.tokopedia.internal.di.components;

import com.kulomady.tokopedia.internal.di.PerActivity;
import com.kulomady.tokopedia.internal.di.modules.ActivityModule;
import com.kulomady.tokopedia.internal.di.modules.ProductModule;
import com.kulomady.tokopedia.view.fragment.ListProductFragment;
import com.kulomady.tokopedia.view.fragment.ProductDetailsFragment;

import dagger.Component;

/**
 * A scope {@link com.kulomady.tokopedia.internal.di.PerActivity} component.
 * Injects product specific Fragments.
 * Created by kulomady on 5/6/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, ProductModule.class})
public interface ProductComponent extends ActivityComponent {
    void inject(ListProductFragment listProductFragment);
    void inject(ProductDetailsFragment productDetailsFragment);

}

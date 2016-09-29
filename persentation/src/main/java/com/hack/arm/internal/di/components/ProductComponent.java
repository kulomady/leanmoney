/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.internal.di.components;

import com.hack.arm.internal.di.modules.ProductModule;
import com.hack.arm.view.fragment.ListProductFragment;
import com.hack.arm.view.fragment.ProductDetailsFragment;
import com.hack.arm.internal.di.PerActivity;
import com.hack.arm.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects product specific Fragments.
 * Created by kulomady on 5/6/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, ProductModule.class})
public interface ProductComponent extends ActivityComponent {
    void inject(ListProductFragment listProductFragment);
    void inject(ProductDetailsFragment productDetailsFragment);

}

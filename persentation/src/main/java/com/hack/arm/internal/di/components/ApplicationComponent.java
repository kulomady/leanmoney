/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.internal.di.components;

/**
 * A component whose lifetime is the life of the application.
 * Created by kulomady on 5/6/16.
 */

import android.content.Context;

import com.hack.arm.Navigation.Navigator;
import com.hack.arm.internal.di.modules.ApplicationModule;
import com.hack.arm.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Navigator navigator();
    Context context();
    com.hack.domain.executor.ThreadExecutor threadExecutor();
    com.hack.domain.executor.PostExecutionThread postExecutionThread();
    com.hack.domain.repository.ProductRepository userRepository();
}

/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm;

import android.app.Application;

import com.hack.arm.internal.di.components.ApplicationComponent;
import com.hack.arm.internal.di.components.DaggerApplicationComponent;
import com.hack.arm.internal.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

/**
 *
 * Created by kulomady on 5/6/16.
 */
public class AndroidApplication  extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

}

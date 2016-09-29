/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.internal.di.components;

import android.app.Activity;

import com.hack.arm.internal.di.PerActivity;
import com.hack.arm.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 *
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 * Created by kulomady on 5/6/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}

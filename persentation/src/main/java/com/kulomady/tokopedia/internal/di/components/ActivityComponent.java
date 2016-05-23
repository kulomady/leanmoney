package com.kulomady.tokopedia.internal.di.components;

import android.app.Activity;

import com.kulomady.tokopedia.internal.di.PerActivity;
import com.kulomady.tokopedia.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 *
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link com.kulomady.tokopedia.internal.di.PerActivity}
 * Created by kulomady on 5/6/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}

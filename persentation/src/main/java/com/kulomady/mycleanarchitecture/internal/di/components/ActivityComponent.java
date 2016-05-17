package com.kulomady.mycleanarchitecture.internal.di.components;

import android.app.Activity;

import com.kulomady.mycleanarchitecture.internal.di.PerActivity;
import com.kulomady.mycleanarchitecture.internal.di.modules.ActivityModule;
import com.kulomady.mycleanarchitecture.internal.di.modules.SigninModule;
import com.kulomady.mycleanarchitecture.view.activity.MainActivity;

import dagger.Component;

/**
 *
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link com.kulomady.mycleanarchitecture.internal.di.PerActivity}
 * Created by kulomady on 5/6/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, SigninModule.class})
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}

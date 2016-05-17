package com.kulomady.mycleanarchitecture.internal.di.components;

import com.kulomady.mycleanarchitecture.internal.di.PerActivity;
import com.kulomady.mycleanarchitecture.internal.di.PerActivity1;
import com.kulomady.mycleanarchitecture.internal.di.modules.ActivityModule;
import com.kulomady.mycleanarchitecture.internal.di.modules.SigninModule;
import com.kulomady.mycleanarchitecture.internal.di.modules.UserModule;
import com.kulomady.mycleanarchitecture.view.activity.MainActivity;
import com.kulomady.mycleanarchitecture.view.fragment.ListUserFragment;
import com.kulomady.mycleanarchitecture.view.fragment.UserDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 * Created by kulomady on 5/6/16.
 */
@PerActivity1
@Component(dependencies = ApplicationComponent.class,modules = SigninModule.class)
public interface SigninComponent {
    void inject(MainActivity mainActivity);


}

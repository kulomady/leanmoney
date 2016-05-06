package com.kulomady.mycleanarchitecture.internal.di.components;

import com.kulomady.mycleanarchitecture.internal.di.PerActivity;
import com.kulomady.mycleanarchitecture.internal.di.modules.ActivityModule;
import com.kulomady.mycleanarchitecture.internal.di.modules.UserModule;
import com.kulomady.mycleanarchitecture.view.fragment.ListUserFragment;
import com.kulomady.mycleanarchitecture.view.fragment.UserDetailsFragment;

import dagger.Component;

/**
 * A scope {@link com.kulomady.mycleanarchitecture.internal.di.PerActivity} component.
 * Injects user specific Fragments.
 * Created by kulomady on 5/6/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    void inject(ListUserFragment userListFragment);
    void inject(UserDetailsFragment userDetailsFragment);

}

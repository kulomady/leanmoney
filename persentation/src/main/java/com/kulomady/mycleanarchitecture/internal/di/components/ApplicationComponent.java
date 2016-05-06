package com.kulomady.mycleanarchitecture.internal.di.components;

/**
 * A component whose lifetime is the life of the application.
 * Created by kulomady on 5/6/16.
 */

import android.content.Context;

import com.kulomady.domain.executor.PostExecutionThread;
import com.kulomady.domain.executor.ThreadExecutor;
import com.kulomady.domain.repository.UserRepository;
import com.kulomady.mycleanarchitecture.internal.di.modules.ApplicationModule;
import com.kulomady.mycleanarchitecture.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    UserRepository userRepository();
}

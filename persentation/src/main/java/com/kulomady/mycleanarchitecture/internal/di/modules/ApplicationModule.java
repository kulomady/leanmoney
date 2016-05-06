package com.kulomady.mycleanarchitecture.internal.di.modules;

import android.content.Context;

import com.kulomady.data.cache.UserCache;
import com.kulomady.data.cache.UserCacheImpl;
import com.kulomady.data.executor.JobExecutor;
import com.kulomady.data.repository.UserDataRepository;
import com.kulomady.domain.executor.PostExecutionThread;
import com.kulomady.domain.executor.ThreadExecutor;
import com.kulomady.domain.repository.UserRepository;
import com.kulomady.mycleanarchitecture.AndroidApplication;
import com.kulomady.mycleanarchitecture.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 *
 * Created by kulomady on 5/6/16.
 */

@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor){
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
    @Provides @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
}

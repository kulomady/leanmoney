package com.kulomady.tokopedia.internal.di.modules;

import android.content.Context;

import com.kulomady.data.cache.ProductCache;
import com.kulomady.data.cache.ProductCacheImpl;
import com.kulomady.data.executor.JobExecutor;
import com.kulomady.data.repository.ProductDataRepository;
import com.kulomady.domain.executor.PostExecutionThread;
import com.kulomady.domain.executor.ThreadExecutor;
import com.kulomady.domain.repository.ProductRepository;
import com.kulomady.tokopedia.AndroidApplication;
import com.kulomady.tokopedia.UIThread;

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
    ProductCache provideUserCache(ProductCacheImpl userCache) {
        return userCache;
    }

    @Provides @Singleton
    ProductRepository provideUserRepository(ProductDataRepository userDataRepository) {
        return userDataRepository;
    }
}

/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.arm.internal.di.modules;

import android.content.Context;

import com.hack.data.cache.ProductCache;
import com.hack.data.executor.JobExecutor;
import com.hack.data.cache.ProductCacheImpl;
import com.hack.data.repository.ProductDataRepository;
import com.hack.domain.executor.ThreadExecutor;
import com.hack.arm.AndroidApplication;
import com.hack.arm.UIThread;

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
    com.hack.domain.executor.PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
    @Provides @Singleton
    ProductCache provideUserCache(ProductCacheImpl userCache) {
        return userCache;
    }

    @Provides @Singleton
    com.hack.domain.repository.ProductRepository provideUserRepository(ProductDataRepository userDataRepository) {
        return userDataRepository;
    }
}

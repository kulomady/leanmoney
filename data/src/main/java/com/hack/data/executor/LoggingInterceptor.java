/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.executor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * Created by kulomady on 5/8/16.
 */
public class LoggingInterceptor implements Interceptor {
    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long timeBefore = System.nanoTime();
        Log.i("RetrofitRequest",String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long timeAfter = System.nanoTime();
        Log.i("RetrofitRequest",String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (timeAfter - timeBefore) / 1e6d, response.headers()));

        return response;
    }
}
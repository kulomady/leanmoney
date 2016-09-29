/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */
package com.hack.data.net;

import android.support.annotation.Nullable;



import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Api Connection class used to retrieve data from the cloud.
 * Implements {@link java.util.concurrent.Callable} so when executed asynchronously can
 * return a value.
 */
public class ApiConnection implements Callable<String> {

  private static final String CONTENT_TYPE_LABEL = "Content-Type";
  private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

  private URL url;
  private String response;

  private ApiConnection(String url) throws MalformedURLException {
    this.url = new URL(url);
  }

  public static ApiConnection createGET(String url) throws MalformedURLException {
    return new ApiConnection(url);
  }

  @Nullable
  public String requestSyncCall() {
    connectToApi();
    return response;
  }

  private void connectToApi() {
    OkHttpClient okHttpClient = this.createClient();
    final Request request = new Request.Builder()
        .url(this.url)
        .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
        .get()
        .build();

    try {
      this.response = okHttpClient.newCall(request).execute().body().string();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private OkHttpClient createClient() {

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.readTimeout(10000, TimeUnit.MILLISECONDS);
    builder.writeTimeout(15000, TimeUnit.MILLISECONDS);

    return builder.build();
  }

  @Override
  public String call() throws Exception {
    return requestSyncCall();
  }
}

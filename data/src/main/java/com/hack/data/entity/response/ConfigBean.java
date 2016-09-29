/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.entity.response;

/**
 * Created by kulomady on 5/22/16.
 */
public class ConfigBean {
    private int backoff_multi;
    private int max_retries;
    private int timeout;

    public int getBackoff_multi() {
        return backoff_multi;
    }

    public void setBackoff_multi(int backoff_multi) {
        this.backoff_multi = backoff_multi;
    }

    public int getMax_retries() {
        return max_retries;
    }

    public void setMax_retries(int max_retries) {
        this.max_retries = max_retries;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}

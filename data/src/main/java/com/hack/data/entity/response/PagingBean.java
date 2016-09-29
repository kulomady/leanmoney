/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.data.entity.response;

/**
 *
 * Created by kulomady on 5/22/16.
 */
public class PagingBean {
    private String uri_next;
    private String uri_previous;

    public String getUri_next() {
        return uri_next;
    }

    public void setUri_next(String uri_next) {
        this.uri_next = uri_next;
    }

    public String getUri_previous() {
        return uri_previous;
    }

    public void setUri_previous(String uri_previous) {
        this.uri_previous = uri_previous;
    }
}


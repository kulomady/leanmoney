/*
 * Created By Kulomady on 9/29/16 1:23 AM
 * Copyright (c) 2016. All rights reserved
 *
 * Last Modified 9/29/16 1:23 AM
 */

package com.hack.domain.item;

import java.util.List;

/**
 *
 * Created by kulomady on 5/22/16.
 */
public class ProductWrapper {
    private String search_url;
    private String share_url;
    private Paging paging;
    private String st;
    private int has_catalog;
    private Object hashtag;
    private Object breadcrumb;
    private int department_id;
    private Object locations;

    private List<ProductItem> products;

    public String getSearch_url() {
        return search_url;
    }

    public void setSearch_url(String search_url) {
        this.search_url = search_url;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public int getHas_catalog() {
        return has_catalog;
    }

    public void setHas_catalog(int has_catalog) {
        this.has_catalog = has_catalog;
    }

    public Object getHashtag() {
        return hashtag;
    }

    public void setHashtag(Object hashtag) {
        this.hashtag = hashtag;
    }

    public Object getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(Object breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public Object getLocations() {
        return locations;
    }

    public void setLocations(Object locations) {
        this.locations = locations;
    }

    public List<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
    }



}

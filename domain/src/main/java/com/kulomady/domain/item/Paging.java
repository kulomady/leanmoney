package com.kulomady.domain.item;

/**
 *
 * Created by kulomady on 5/22/16.
 */
public class Paging {
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


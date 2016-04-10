package com.ksimeo.nazaru.core.models;

import java.util.List;

/**
 * Created by @Ksimeo on 18.03.2015
 */
public class Parcel<T> {

    private int count;
    private List<T> page;

    public Parcel() { }

    public Parcel(int count, List<T> page) {
        this.count = count;
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getPage() {
        return page;
    }

    public void setPage(List<T> page) {
        this.page = page;
    }
}

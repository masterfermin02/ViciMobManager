package com.viciexperts.fpapps.vicimobmanager.repository;

import java.util.List;

/**
 * Created by maste on 11/20/2017.
 */

public interface Repository<T> {
    public static final int FIRST = 0;
    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    T get(Specification specification);

    void remove(T item);

    void remove(Specification specification);

    List<T> query(Specification specification);
}

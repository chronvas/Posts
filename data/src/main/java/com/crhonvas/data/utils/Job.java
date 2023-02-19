package com.crhonvas.data.utils;

/**
 * Perform an operation on each item of type 'T'
 */
public interface Job<T> {
    void perform(T t);
}

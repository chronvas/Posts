package com.crhonvas.data.utils;

/**
 * For each item T, map into item S
 */

public interface Mapper<T, S> {
    S map(T t);
}

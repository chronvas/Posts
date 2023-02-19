package com.crhonvas.data.utils;

import android.support.annotation.Nullable;

/**
 * Testing if item t returns true on predicate
 */
public interface Condition<T> {
    boolean isTrue(@Nullable T t);
}

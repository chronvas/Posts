package com.crhonvas.data.utils;

import androidx.annotation.Nullable;

/**
 * Testing if item t returns true on predicate
 */
public interface Condition<T> {
    boolean isTrue(@Nullable T t);
}

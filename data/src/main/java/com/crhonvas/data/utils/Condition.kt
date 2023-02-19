package com.crhonvas.data.utils

/**
 * Testing if item t returns true on predicate
 */
interface Condition<T> {
    fun isTrue(t: T?): Boolean
}
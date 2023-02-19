package com.crhonvas.data.utils

/**
 * Perform an operation on each item of type 'T'
 */
interface Job<T> {
    fun perform(t: T)
}
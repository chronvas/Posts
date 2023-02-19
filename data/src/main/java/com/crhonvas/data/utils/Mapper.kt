package com.crhonvas.data.utils

/**
 * For each item T, map into item S
 */
interface Mapper<T, S> {
    fun map(t: T): S
}
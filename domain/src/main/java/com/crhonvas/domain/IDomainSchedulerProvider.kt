package com.crhonvas.domain

import io.reactivex.Scheduler

interface IDomainSchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}
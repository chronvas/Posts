package com.chronvas.btest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class Main : Application() {
    override fun onCreate() {
        initTimber()
        super.onCreate()
        registerRxErrorHandler()
    }

    companion object {
        private fun initTimber() {
            if (BuildConfig.DEBUG) {
                Timber.plant(DebugTree())
            }
        }

        private fun registerRxErrorHandler() {
            RxJavaPlugins.setErrorHandler {
                var e = it
                if (e is UndeliverableException) {
                    e = e.cause
                }
                if (e is IOException || e is SocketException) {
                    // fine, irrelevant network problem or API that throws on cancellation
                    return@setErrorHandler
                }
                if (e is InterruptedException) {
                    // fine, some blocking code was interrupted by a dispose call
                    return@setErrorHandler
                }
                if (e is NullPointerException || e is IllegalArgumentException) {
                    // that's likely a bug in the application
                    Thread.currentThread().uncaughtExceptionHandler
                        ?.uncaughtException(Thread.currentThread(), e)
                    return@setErrorHandler
                }
                if (e is IllegalStateException) {
                    // that's a bug in RxJava or in a custom operator
                    Thread.currentThread().uncaughtExceptionHandler
                        ?.uncaughtException(Thread.currentThread(), e)
                    return@setErrorHandler
                }
                Timber.e(e, "Undeliverable exception received, not sure what to do")
            }
        }
    }
}
package com.example.roompractice.presentation.base

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * The Base Application which will live entire lifecycle of an application.
 *
 * I want to make our AppComponent live in the entire lifecycler of an
 * application so we can instantiate it on applicationInjection
 * overrided method which is comming from [DaggerApplication]
 */

class BaseApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector <out DaggerApplication>? {
        return null
    }
}
package com.example.roompractice.presentation.base

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BaseApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector <out DaggerApplication>? {
        return null
    }
}
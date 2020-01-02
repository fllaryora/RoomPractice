package com.example.roompractice.di

import com.example.roompractice.di.auth.AuthModule
import com.example.roompractice.di.auth.AuthScope
import com.example.roompractice.di.auth.AuthViewModelModule
import com.example.roompractice.di.main.MainFragmentBuilderModule
import com.example.roompractice.di.main.MainModule
import com.example.roompractice.di.main.MainScope
import com.example.roompractice.di.main.MainViewModelModule
import com.example.roompractice.presentation.auth.AuthActivity
import com.example.roompractice.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Because of [ActivityBuilderModule] is a dagger module It has the annotation [Module].
 *
 * The [Module] is where the dependencies live. And you should add it to the components.
 *
 * This class is abstract because it says every activity is a potential client.
 *
 * This Class [ActivityBuilderModule] is responsible for for android injection
 * for the activity with in the application to avoid the separate injection in each activity
 *
 * {@linkplain dagger.android.AndroidInjection#inject(Activity)}
 *
 * AuthViewModel can be access from Auth Activity
 * only so it is the concept of sub-modules
 *
 */

@Module
abstract class ActivityBuilderModule {

    /**
     * Let dagger knows that all activity is a potential client
     * Automatically create sub-component
     *
     * Here it is the magic of scopping !!!
     * During the set of particular module to  authActivity
     * @return
     *
     * Apply the auth scope to the sub component,
     * that subComponent will own authScope
     *
     */
    @AuthScope
    @ContributesAndroidInjector (
        modules = [
            AuthViewModelModule::class,
            AuthModule::class
        ]
    )
    abstract fun contributeAuthActivity() : AuthActivity


    @MainScope
    @ContributesAndroidInjector (
        modules = [
            MainFragmentBuilderModule::class,
            MainViewModelModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity() : MainActivity
}
package com.example.roompractice.di

import com.example.roompractice.presentation.auth.AuthActivity
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
 * for the activity with in the application to avoid the seprate injection in each activity
 *
 * {@linkplain dagger.android.AndroidInjection#inject(Activity)}
 *
 * {@link com.example.advancedagger2.viewmodel.AuthViewModel} can be access from Auth Activity
 * only so it is the concept of sub-modules
 *
 */

@Module
abstract class ActivityBuilderModule {
    /**
     * Let dagger knows that all activity is a potential client
     * Automatically create sub-component
     *
     * @return
     */
    @ContributesAndroidInjector
    abstract fun contributeAuthActivity() : AuthActivity
}
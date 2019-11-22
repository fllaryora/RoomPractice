package com.example.roompractice.di.main

import com.example.roompractice.presentation.main.post.PostsFragment
import com.example.roompractice.presentation.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Main Fragment Builder Module which is only accessible from Main Activity
 */
@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment() : PostsFragment
}
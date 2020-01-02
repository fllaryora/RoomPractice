package com.example.roompractice.di

import androidx.lifecycle.ViewModelProvider
import com.example.roompractice.presentation.viewmodel.ViewModelProviderFactory

import dagger.Binds
import dagger.Module

/**
 * ViewModelFactoryModule responsible for providing [ViewModelProviderFactory]
 *
 * Annotated with Module to tell dagger it is a module to provide [ViewModelProviderFactory]
 *
 * Annotated with bind annotation to efficiently provide dependencies similar to provides annotation
 */
@Module
abstract class ViewModelFactoryModule {

    /**
     * Binds is a f***ing handrail
     * This is similar to using an annotation provider that receives a dependency
     *  and returns the same dependency without doing anything.
     */
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory)
            :ViewModelProvider.Factory
}
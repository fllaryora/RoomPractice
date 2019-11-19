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
     * Binds es un puto pasamano
     * Esto es similar a usar un AT provider
     * que recive una dependencia y retorna la misma dependencia sin hacer nada.
     */
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory)
            :ViewModelProvider.Factory
}
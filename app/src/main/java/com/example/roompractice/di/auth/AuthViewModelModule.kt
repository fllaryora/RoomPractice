package com.example.roompractice.di.auth

import androidx.lifecycle.ViewModel
import com.example.roompractice.di.ViewModelKey
import com.example.roompractice.presentation.auth.AuthViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    /**
     * Binds the auth view model dependency with [ViewModelKey] to group similar [ViewModel]
     *
     * Under the hood it is providing [AuthViewModel]
     *
     * With [IntoMap] annotation it warns to XXX?? It going to use it.
     * In my dictionary [ViewModelKey].
     * AuthViewModel is the key, and ViewModel is the value
     *
     */

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel) : ViewModel

}
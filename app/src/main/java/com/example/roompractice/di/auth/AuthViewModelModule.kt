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
     * Con la anotacion [IntoMap], le aviso que voy a usar
     * mi diccionario enjendro de la naturaleza [ViewModelKey].
     * AuthViewModel es la key, y el ViewModel es el valor
     *
     */

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel) : ViewModel

}
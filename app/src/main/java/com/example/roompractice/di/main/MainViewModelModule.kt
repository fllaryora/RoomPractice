package com.example.roompractice.di.main

import androidx.lifecycle.ViewModel
import com.example.roompractice.di.ViewModelKey
import com.example.roompractice.presentation.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Profile View Model Module
 */
@Module
abstract class MainViewModelModule {


    /**
     * Binds the Profile View Model dependency with [ViewModelKey] to group similar [ViewModel]
     *
     * Under the hood it is providing [ProfileViewModel]
     */
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel) : ViewModel
}
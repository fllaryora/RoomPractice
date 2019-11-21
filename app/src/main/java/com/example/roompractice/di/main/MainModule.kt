package com.example.roompractice.di.main

import com.example.roompractice.data.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    /**
     * Provides auth api
     *
     * @param retrofit The retrofit instance which can be access from app component
     */
    @Provides
    internal fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}
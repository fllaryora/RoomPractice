package com.example.roompractice.di.auth

import com.example.roompractice.data.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    /**
     * Provides auth api
     *
     * @param retrofit The retrofit instance which can be access from app component
     */
    @AuthScope
    @Provides
    internal fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}
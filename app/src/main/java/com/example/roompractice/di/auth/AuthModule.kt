package com.example.roompractice.di.auth

import com.example.roompractice.data.network.auth.AuthApi
import com.example.roompractice.data.repository.auth.LoginRepository
import com.example.roompractice.data.room.dao.UsersDAO
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

    /**
     * Provides Login Remote Imp
     *
     * @param authApi The retrofit auth api
     *  @param userDao The retrofit auth api
     */
    @AuthScope
    @Provides
    internal fun provideLoginRepository(authApi: AuthApi, userDao: UsersDAO): LoginRepository {
        return LoginRepository(authApi, userDao)
    }
}
package com.example.roompractice.di.main

import android.app.Application
import androidx.room.Room
import com.example.roompractice.data.network.main.MainApi
import com.example.roompractice.data.room.MyDataBase
import com.example.roompractice.data.room.dao.PostDAO
import com.example.roompractice.data.room.dao.UsersDAO
import com.example.roompractice.presentation.main.post.PostsRecyclerAdapter
import com.example.roompractice.utils.Constants
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
    @MainScope
    @Provides
    internal fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }

    @MainScope
    @Provides
    internal fun providePostsRecyclerAdapter(): PostsRecyclerAdapter {
        return PostsRecyclerAdapter()
    }

    /**
     * room para MainScope.
     */
    @MainScope
    @Provides
    internal fun provideDatabase(application: Application): MyDataBase {
        return Room.databaseBuilder(application.applicationContext,
            MyDataBase::class.java, Constants.DATABASE_NAME
        ).build()
    }

    @MainScope
    @Provides
    internal fun provideUsersDAO(database: MyDataBase): UsersDAO {
        return database.usersDAO()
    }

    @MainScope
    @Provides
    internal fun providePostDAO(database: MyDataBase): PostDAO {
        return database.postDAO()
    }

}
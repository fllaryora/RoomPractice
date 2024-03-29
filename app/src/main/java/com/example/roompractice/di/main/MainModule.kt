package com.example.roompractice.di.main

import com.example.roompractice.data.network.main.MainApi
import com.example.roompractice.data.repository.main.PostRepository
import com.example.roompractice.data.room.MyDataBase
import com.example.roompractice.data.room.dao.PostDAO
import com.example.roompractice.presentation.main.post.PostsRecyclerAdapter
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

    @MainScope
    @Provides
    internal fun providePostDAO(database: MyDataBase): PostDAO {
        return database.postDAO()
    }

    @MainScope
    @Provides
    internal fun providePostRepository( mainApi: MainApi, postDAO: PostDAO): PostRepository {
        return PostRepository(mainApi, postDAO)
    }

}
package com.example.roompractice.data.network.main

import com.example.roompractice.data.model.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface MainApi {

    /**
     * [Flowable] is used because it is easy to convert into live data
     */
    // /posts?userId=1/
    @GET("posts")
    fun getUserById(@Query("id") id: Int): Flowable<List<Post>>

}
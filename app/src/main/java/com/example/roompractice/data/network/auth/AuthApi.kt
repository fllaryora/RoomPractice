package com.example.roompractice.data.network.auth

import com.example.roompractice.data.model.Users
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    /**
     * [Single] is used because it is easy to convert into live data
     */
    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Single<Users>

}
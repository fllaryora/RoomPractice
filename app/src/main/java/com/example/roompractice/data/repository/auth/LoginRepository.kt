package com.example.roompractice.data.repository.auth

import android.util.Log
import com.example.roompractice.data.model.Users
import com.example.roompractice.data.network.auth.AuthApi
import com.example.roompractice.data.room.dao.UsersDAO
import com.example.roompractice.presentation.auth.AuthResource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginRepository @Inject constructor(private val authApi: AuthApi, private val userDao: UsersDAO) {
    fun loginUser(userID: Int): Flowable< AuthResource<Users> > {
        val dataFromApi : Single<Users> = authApi.getUserById(userID)
       .doAfterSuccess {
           Log.d("LoginRepository","2) showing progress on:" + Thread.currentThread().name)
           userDao.insertUser(transFormApiToBaseFormat(it))
       }
        val dataFromBase : Single<Users> = userDao.getItemById(userID.toLong())
            .flatMap { user -> Single.just(transFormBaseToApiFormat(user)) }

        return dataFromApi.concatWith(dataFromBase)
            .firstOrError()
            .flatMap { user -> Single.just(AuthResource.authenticated(user)) }
            .onErrorReturnItem(AuthResource.error("Could not authenticated", null))
            .toFlowable() //flowable is publisher for some reason
            .subscribeOn(Schedulers.io())
    }

    fun transFormBaseToApiFormat(databaseUser: com.example.roompractice.data.room.model.Users) : Users {
        val user: Int  = databaseUser.id?.toInt() ?: -1
        return Users(user , databaseUser.userName?:"" ,
            databaseUser.emailAddress ?:"", databaseUser.website?:"")
    }

    fun transFormApiToBaseFormat(apiUser: Users) : com.example.roompractice.data.room.model.Users {
        return com.example.roompractice.data.room.model.Users(apiUser.id?.toLong(),apiUser.userName,
            apiUser.emailAddress, apiUser.website)
    }
}
package com.example.roompractice.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roompractice.data.model.Users
import com.example.roompractice.data.network.auth.AuthApi
import com.example.roompractice.presentation.SessionManager
import io.reactivex.internal.util.HalfSerializer.onNext
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function

/**
 * Auth Api View Model Class
 *  Primero se la extiende de [ViewModel]
 *  Segundo se crea el constructor vacio con un AT [Inject]
 */
class AuthViewModel @Inject constructor (private var authApi: AuthApi,
                                         private var sessionManager: SessionManager): ViewModel(){

    companion object {
        val TAG = AuthViewModel::class.java.name
    }
        private val compositeDisposable = CompositeDisposable()


    fun authenticateUserById(userID: Int) {
        sessionManager.authenticatedWithId(queryUserId(userID))
    }

    fun queryUserId(userID: Int) : LiveData<AuthResource<Users>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUserById(userID)
                .onErrorReturn(object : Function<Throwable, Users> {
                    override fun apply(t: Throwable): Users {
                        val user = Users()
                        user.id = -1
                        return user
                    }

                })
                .map(object : Function<Users, AuthResource<Users>> {
                    override fun apply(user: Users): AuthResource<Users> {
                        if (user.id == -1) {
                            return AuthResource.error("Could not authenticated", null)
                        }
                        return AuthResource.authenticated(user)
                    }

                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeUser(): LiveData<AuthResource<Users>> {
        return sessionManager.getAuthUser()
    }



    override fun onCleared() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        super.onCleared()
    }

    val showProgressBar : MutableLiveData<Boolean> = MutableLiveData()

    fun setProgressBar(isShowed :Boolean){
        showProgressBar.value = isShowed
    }

    val userModel : MutableLiveData<Users> = MutableLiveData()

    init {
        showProgressBar.value = false
        userModel.value = Users()
    }
}
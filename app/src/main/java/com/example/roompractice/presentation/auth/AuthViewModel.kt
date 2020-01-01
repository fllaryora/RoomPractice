package com.example.roompractice.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roompractice.data.model.Users
import com.example.roompractice.data.repository.auth.LoginRepository
import com.example.roompractice.presentation.SessionManager
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable


/**
 * Auth Api View Model Class
 *  Primero se la extiende de [ViewModel]
 *  Segundo se crea el constructor vacio con un AT [Inject]
 */
class AuthViewModel @Inject constructor (private var repository: LoginRepository,
                                         private var sessionManager: SessionManager): ViewModel(){

    companion object {
        val TAG = AuthViewModel::class.java.name
    }

    private val compositeDisposable = CompositeDisposable()

    fun authenticateUserById() {
        val userID :Int = userModel.value?.id?:-1
        sessionManager.authenticatedWithId(queryUserId(userID))
    }

    fun queryUserId(userID: Int) : LiveData<AuthResource<Users>> {
        return LiveDataReactiveStreams.fromPublisher(
            repository.loginUser(userID)

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

    fun canPerformLogin():Boolean {
        return userModel.value?.id != null
    }
}
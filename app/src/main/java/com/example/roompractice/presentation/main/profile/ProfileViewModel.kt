package com.example.roompractice.presentation.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roompractice.data.model.Users
import com.example.roompractice.presentation.SessionManager
import javax.inject.Inject

/**
 * Profile View Model
 */
class ProfileViewModel @Inject constructor(private var sessionManager: SessionManager) : ViewModel() {

    companion object {
        val TAG = ProfileViewModel::class.java.name
    }

    fun getAuthenticatedUser() = sessionManager.getAuthUser()

    val userModel : MutableLiveData<Users> = MutableLiveData()

    init {
        userModel.value = Users()
    }


    fun setErrorMessage(message: String?) {
        userModel.value?.userName = "Error"
        userModel.value?.website = "Error"
        userModel.value?.emailAddress = message?:"Error"
    }

    fun setUserInfo(data: Users?) {
        userModel.value = data
    }
}
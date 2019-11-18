package com.example.roompractice.presentation.main.profile

import androidx.lifecycle.ViewModel
import com.example.roompractice.presentation.SessionManager
import javax.inject.Inject

/**
 * Profile View Model
 */
class ProfileViewModel @Inject constructor(sessionManager: SessionManager) : ViewModel() {

    lateinit var sessionManager: SessionManager

    companion object {
        val TAG = ProfileViewModel::class.java.name
    }

    init {
        this.sessionManager = sessionManager
    }

    fun getAuthenticatedUser() = sessionManager.getAuthUser()

}
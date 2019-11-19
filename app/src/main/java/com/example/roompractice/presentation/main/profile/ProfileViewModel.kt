package com.example.roompractice.presentation.main.profile

import androidx.lifecycle.ViewModel
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

}
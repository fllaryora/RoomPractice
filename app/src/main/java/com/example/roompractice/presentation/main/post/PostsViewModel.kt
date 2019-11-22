package com.example.roompractice.presentation.main.post

import androidx.lifecycle.ViewModel
import com.example.roompractice.data.network.main.MainApi
import com.example.roompractice.presentation.SessionManager
import javax.inject.Inject

class PostsViewModel  @Inject constructor(private var sessionManager: SessionManager,
                                          private var mainApi: MainApi): ViewModel() {
    companion object {
        val TAG = PostsViewModel::class.java.name
    }
}
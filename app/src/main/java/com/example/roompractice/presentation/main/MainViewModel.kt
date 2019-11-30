package com.example.roompractice.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roompractice.data.network.main.MainApi
import com.example.roompractice.presentation.SessionManager
import javax.inject.Inject

class MainViewModel@Inject constructor (private var mainApi: MainApi,
                                        private var sessionManager: SessionManager ): ViewModel() {
    companion object {
        var TAG = MainViewModel::class.java.name
    }

    val showProgressBar : MutableLiveData<Boolean> = MutableLiveData(false)

    fun setProgressBar(isShowed :Boolean){
        showProgressBar.value = isShowed
    }

}
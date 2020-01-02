package com.example.roompractice.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel@Inject constructor() : ViewModel() {
    companion object {
        var TAG = MainViewModel::class.java.name
    }

    val showProgressBar : MutableLiveData<Boolean> = MutableLiveData(false)

}
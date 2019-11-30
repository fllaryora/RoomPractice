package com.example.roompractice.presentation.databinding

import android.view.View
import androidx.databinding.BindingAdapter

class OurBinderAdapters {
    companion object {
        @JvmStatic @BindingAdapter("shouldIShowTheView")
        fun shouldIShowTheView(view: View, isShowed: Boolean) {
            if(isShowed){
                view.visibility = View.VISIBLE
            } else  {
                view.visibility = View.GONE
            }
        }
    }
}
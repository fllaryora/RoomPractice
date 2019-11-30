package com.example.roompractice.presentation.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText
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

         @BindingAdapter("android:text") @JvmStatic
        fun setIntegerTextView(view: TextInputEditText, value: Int?) {
            if (value != null) {
                view.setText("$value")
            }
        }

         @InverseBindingAdapter(attribute = "android:text") @JvmStatic
        fun getIntegerTextView(view: TextInputEditText):Int? {
             try{
                 return Integer.parseInt(view.text.toString())
             } catch (ignore:Exception){}
            return  null
        }
    }
}
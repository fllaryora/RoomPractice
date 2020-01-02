package com.example.roompractice.presentation.databinding

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityBindingProperty<out T : ViewDataBinding>(
    @LayoutRes private val resId: Int
) : ReadOnlyProperty<Activity, T> {

    /**
     * the binding variable is created only once.
     */

    private var binding: T? = null

    override operator fun getValue(
        thisRef: Activity,
        property: KProperty<*>
    ): T = binding ?: createBinding(thisRef).also { binding = it }

    private fun createBinding(
        activity: Activity
    ): T = DataBindingUtil.setContentView(activity, resId)
}

/**
 * This is an extension function that calls to [DataBindingUtil.inflate].
 * Gets the data binding for layout
 */
fun <B : ViewDataBinding> Fragment.withTwoWayBinding(
    inflater: LayoutInflater, @LayoutRes layoutResId: Int,
    parent: ViewGroup?, function: B.() -> Unit = {}
): B {
    val binding: B = DataBindingUtil.inflate(inflater, layoutResId, parent, false)
    binding.lifecycleOwner = this
    binding.function()
    return binding
}

/*
fun <B : ViewDataBinding> FragmentActivity.withBinding(
    @LayoutRes layoutResId: Int,
    function: B.() -> Unit = {}
): B {
    val binding: B = DataBindingUtil.setContentView(this, layoutResId)
    binding.lifecycleOwner = this
    binding.function()
    return binding
}

fun <B : ViewDataBinding> ViewGroup.withBinding(@LayoutRes layoutResId: Int): B =
    DataBindingUtil.inflate(LayoutInflater.from(context), layoutResId, this, false)

*/

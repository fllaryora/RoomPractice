package com.example.roompractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * [ViewModelProviderFactory] is the class that facilitates the injection of viewmodels into dagger
 * This workarround is to allow a viewModel to receive injections.
 * In the future google is supposed to change android and you won't need this class anymore.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject
constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) { // if the viewmodel has not been created

            // loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for ((key, value) in creators) {

                // if it's allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        // if this is not one of the allowed keys, throw exception
        requireNotNull(creator) { "unknown model class $modelClass" }

        // return the Provider
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    companion object {
        val TAG = ViewModelProviderFactory::class.java.name
    }
}
package com.example.roompractice.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.roompractice.data.model.Users
import com.example.roompractice.presentation.auth.AuthResource
import androidx.lifecycle.Observer
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Session Manager Class
 * Guarda la informacion para saver si el usuario esta autenticado o no.
 *
 * se evita tener un MediatorLiveData en cada activity
 */
@Singleton
class SessionManager @Inject constructor() {

    val TAG = SessionManager::class.java.name

    //Mediator live data is a sub class of live data which may observe other LiveData
    // objects and react on OnChanged events from them.
    private val cachedUser = MediatorLiveData<AuthResource<Users>>()

    fun authenticatedWithId(source: LiveData<AuthResource<Users>>) {
        if(cachedUser != null) {
            cachedUser.value = AuthResource.loading(null)
            cachedUser.addSource(source, Observer {
                cachedUser.value = it
                //apenas lo deja de usar lo remueve al source
                cachedUser.removeSource(source)
            })
        } else {
            Log.d(TAG, "authenticatedWithId: previous session detected")
        }
    }

    fun logout() {
        Log.d(TAG, "logout: logging out..")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser() : LiveData<AuthResource<Users>> {
        return cachedUser
    }


}
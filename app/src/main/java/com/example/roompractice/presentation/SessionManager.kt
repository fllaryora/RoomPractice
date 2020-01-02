package com.example.roompractice.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.roompractice.data.model.Users
import com.example.roompractice.presentation.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Session Manager Class
 * It Saves information to know if the user is authenticated or not.
 *
 * It avoid to have MediatorLiveData in each activity
 */
@Singleton
class SessionManager @Inject constructor() {

    companion object {
        val TAG = SessionManager::class.java.name
    }
    //Mediator live data is a sub class of live data which may observe other LiveData
    // objects and react on OnChanged events from them.
    private val cachedUser = MediatorLiveData<AuthResource<Users>>()

    fun authenticatedWithId(source: LiveData<AuthResource<Users>>) {
        if(cachedUser!= null) {
        //TODO
        //if(cachedUser.value == null) {
            cachedUser.value = AuthResource.loading(null)
            cachedUser.addSource(source) {
                cachedUser.value = it
                //as soon as it stops using it it removes it to the source
                cachedUser.removeSource(source)
            }
        } else {
            Log.d(TAG, "authenticatedWithId: previous session detected")
            //todo in this line it will be hanged. Please avoid it.
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
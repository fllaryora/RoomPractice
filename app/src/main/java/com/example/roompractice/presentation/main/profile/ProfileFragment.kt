package com.example.roompractice.presentation.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roompractice.R
import com.example.roompractice.data.model.Users
import com.example.roompractice.presentation.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject
import com.example.roompractice.presentation.auth.AuthResource

/**
 * Profile Fragment
 */
class ProfileFragment : DaggerFragment() {


    lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    companion object {
        val TAG = ProfileFragment::class.java.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.d(TAG, "Profile Fragment")

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "Profile View Holder was created....")

        profileViewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel::class.java)

        subscribeObservers()

    }

    private fun subscribeObservers() {
        /**
         * Se tiene que tener en cuenta que el ciclo de vida de los fragments es diferente al ciclo de vida
         * de las activities.
         * Por esta razon es diferente al de la activity
         * Y debemos estar seguros de remover los viejos vinagres (los observers) todo al rededor.
         */
        profileViewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        profileViewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserInfo(resource.data)
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        setErrorMessage(resource.message)
                    }

                    else -> {

                    }
                }
            }
        })
    }

    private fun setErrorMessage(message: String?) {
        email.text = message
        username.text = "Error"
        website.text = "Error"
    }

    private fun setUserInfo(data: Users?) {
        email.text = data?.emailAddress
        username.text = data?.userName
        website.text = data?.website
    }
}
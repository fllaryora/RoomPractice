package com.example.roompractice.presentation.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.roompractice.R
import com.example.roompractice.presentation.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import androidx.lifecycle.Observer
import com.example.roompractice.databinding.ActivityAuthBinding
import com.example.roompractice.presentation.databinding.ActivityBindingProperty
import com.example.roompractice.presentation.main.MainActivity

class AuthActivity : DaggerAppCompatActivity() {

    companion object {
        val TAG = AuthActivity::class.java.name
    }

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    /**
     * viewModelFactory will be in charge of to instantiate  viewModel
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel

    /**
     * binding by lazy ActivityBindingProperty
     */
    val binding : ActivityAuthBinding by ActivityBindingProperty(R.layout.activity_auth)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Having the lifecycle dependency set in the build.gradle file
        // we are able to connect the activity with viewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)
        /**
         *  two way
         *  but it isn't called here activity never create the view
         *
         */
        binding.lifecycleOwner = this
        /**
         * This bind the databinding with MVVVM
         */
        binding.viewModel = viewModel
        setLogo()
        loginButtonClick()
        subscribeObserver()
    }

    /**
     * Set the logo in the image view
     */
    private fun setLogo() {
        requestManager
            .load(logo)
            .into(binding.loginLogo)
    }

    private fun loginButtonClick() {
        binding.loginButton.setOnClickListener {
            if (!viewModel.canPerformLogin()) {
                return@setOnClickListener
            }
            atemptLogin()
        }
    }

    private fun atemptLogin() {
        viewModel.authenticateUserById()
    }


    private fun subscribeObserver() {
        viewModel.observeUser().observe(this, Observer {authResource ->
            //Log.d(TAG,  "On Change: ${authResource.emailAddress}")
            /**
             * Here It handle the change of status of auth
             */
            when(authResource.status) {
                AuthResource.AuthStatus.LOADING -> {
                    viewModel.setProgressBar(true)
                }

                AuthResource.AuthStatus.AUTHENTICATED -> {
                    viewModel.setProgressBar(false)
                    Log.d(TAG,  "Login Success: ${authResource.data?.emailAddress}")
                    onLoginSuccess()
                }

                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    viewModel.setProgressBar(false)
                }

                AuthResource.AuthStatus.ERROR -> {
                    //TODO To detect network issues
                    viewModel.setProgressBar(false)
                    Toast.makeText(this,
                        "${authResource.message} \nDid you entered user id between 1 and 10?",
                        Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    /**
     * Navigate to [MainActivity] from [AuthActivity] on login success
     */
    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

package com.example.roompractice.presentation.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.roompractice.R
import com.example.roompractice.presentation.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    /**
     * Se va a encargar de instanciar el viewModel
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Teniendo la dependencia del lifecycle puesta en el build.gradle
        // se puede conectar el activity con el viewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)

        setLogo()
    }

    /**
     * Set the logo in the image view
     */
    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }
}

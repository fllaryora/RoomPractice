package com.example.roompractice.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.roompractice.R
import com.example.roompractice.databinding.ActivityMainBinding
import com.example.roompractice.presentation.base.BaseActivity
import com.example.roompractice.presentation.databinding.ActivityBindingProperty
import com.example.roompractice.presentation.viewmodel.ViewModelProviderFactory
import com.example.roompractice.utils.Constants
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val navController by lazy { findNavController(nav_host_fragment) }

    /**
     * Se va a encargar de instanciar el viewModel
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel

    /**
     * binding by lazy ActivityBindingProperty
     */
    val binding : ActivityMainBinding by ActivityBindingProperty(R.layout.activity_main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Teniendo la dependencia del lifecycle puesta en el build.gradle
        // se puede conectar el activity con el viewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        /**
         *  two way
         *  but it isn't called here activity never create the view
         *
         */
        binding.lifecycleOwner = this
        initNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.logout -> {
                sessionManager.logout()
                return Constants.CLICK_IS_CONSUMED
            }

            android.R.id.home -> {
                if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    return Constants.CLICK_IS_CONSUMED
                } else {
                    return Constants.CLICK_IS_NOT_CONSUMED
                }
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    /**
     *  to inflate on [MainActivity]
     */
    private fun initNavigation() {

        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)
        /**
         * para que consuma los clicks locales en onNavigationItemSelected
         */
        nav_view.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_profile -> {
                    /**
                     * Conditional Navigation:
                     * Pop up to a given destination before navigating.
                     * This pops all non-matching destinations from the back stack
                     * until this destination is found.
                     */
                    val popUpTo = NavOptions.Builder().setPopUpTo(
                        R.id.main,
                        true
                    ).build()
                    navController.navigate(R.id.profileScreen, null, popUpTo)
                Constants.CLICK_IS_CONSUMED
            }
            R.id.nav_posts -> {
                if(isValidDestination(R.id.postScreen)) {
                    navController.navigate(R.id.postScreen)
                }
            }
            else -> {
            }
        }
        item.isChecked = true
        /**
         * lo llevamos de vuelta al START si no seleccionamos nada al cerrar.
         */
        drawer_layout.closeDrawer(GravityCompat.START)
        return Constants.CLICK_IS_CONSUMED
    }

    /**
     * Asumiendo que el up button el = pero con 3 barras.
     * Maneja el up button delegando el comportamiento
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawer_layout)
    }

    private fun isValidDestination(destination:Int):Boolean {
        return destination != navController.currentDestination?.id
    }
}
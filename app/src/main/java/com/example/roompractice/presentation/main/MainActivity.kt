package com.example.roompractice.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.roompractice.R
import com.example.roompractice.databinding.ActivityMainBinding
import com.example.roompractice.presentation.base.BaseActivity
import com.example.roompractice.presentation.databinding.ActivityBindingProperty
import com.example.roompractice.presentation.viewmodel.ViewModelProviderFactory
import com.example.roompractice.utils.Constants
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    /**
     * It will be in charge of to instantiate the viewModel
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
        // Having the lifecycle dependency set in the build.gradle file
        // we are able to connect the activity with viewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
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
                return if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    Constants.CLICK_IS_CONSUMED
                } else {
                    Constants.CLICK_IS_NOT_CONSUMED
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

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        /**
         * to consume the local clicks in onNavigationItemSelected
         */
        binding.navView.setNavigationItemSelectedListener(this)

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
         * we take it back to START if we don't select anything at closing.
         */
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return Constants.CLICK_IS_CONSUMED
    }

    /**
     * Assuming that the up-button manages the up button by delegating the behavior
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,binding.drawerLayout)
    }

    private fun isValidDestination(destination:Int):Boolean {
        return destination != navController.currentDestination?.id
    }
}
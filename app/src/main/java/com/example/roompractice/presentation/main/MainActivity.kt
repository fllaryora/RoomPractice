package com.example.roompractice.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.roompractice.R
import com.example.roompractice.presentation.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //testFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.logout -> {
                sessionManager.logout()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Test the fragment to inflate on [MainActivity]
     */
    /*private fun testFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ProfileFragment())
            .commit()
    }*/
}
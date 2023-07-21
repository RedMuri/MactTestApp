package com.example.macttestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.navController }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationDrawer()
        val preferManager = PreferenceManager.getDefaultSharedPreferences(application)
        val baseUrl = preferManager.getString(MactTestApp.BASE_URL, null)
        if (baseUrl == null)
            navController.navigate(R.id.settingsFragment)
    }

    private fun setupNavigationDrawer() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.quotesFragment,
                R.id.productsFragment,
                R.id.settingsFragment
            ), findViewById(R.id.drawer_layout)
        )
        setupActionBarWithNavController(
            navController, appBarConfiguration
        )
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)|| super.onSupportNavigateUp()
    }
}
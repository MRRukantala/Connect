package com.example.connect.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.connect.R
import com.example.connect.databinding.ActivityMainAppBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainAppBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainAppBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_activity_main_app
        )
        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }

        val navView: BottomNavigationView = binding.navView

        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _,
                                                        destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> navView.visibility = View.VISIBLE
                R.id.dashboardFragment -> navView.visibility = View.VISIBLE
                R.id.notificationsFragment -> navView.visibility = View.VISIBLE
                R.id.containerInfoPendidikanFragment -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }
    }


}


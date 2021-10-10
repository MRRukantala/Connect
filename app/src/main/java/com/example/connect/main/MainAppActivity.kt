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
    private lateinit var appBarConfiguration: AppBarConfiguration

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

//        navController = this.findNavController(R.id.nav_host_fragment_activity_main_app)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _,
                                                        destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> navView.visibility = View.VISIBLE
                R.id.dashboardFragment -> navView.visibility = View.VISIBLE
                R.id.notificationsFragment -> navView.visibility = View.VISIBLE
                R.id.mainMenuOptionFragment -> navView.visibility = View.VISIBLE
                else -> navView.visibility = View.GONE
            }
        }

//        NavigationUI.setupActionBarWithNavController(this,navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

}


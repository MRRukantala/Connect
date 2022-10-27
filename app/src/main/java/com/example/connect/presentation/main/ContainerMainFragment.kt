package com.example.connect.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.connect.R
import com.example.connect.databinding.FragmentMainAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerMainFragment : Fragment() {

    private lateinit var binding: FragmentMainAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainAppBinding.inflate(inflater,  container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerMenuFragment = this
        val nav = childFragmentManager.findFragmentById(
            R.id.nav_host_fragment_menu
        ) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bnMenu, nav.navController)

        nav.navController.addOnDestinationChangedListener { _, main, _ ->
            when (main.id) {
                R.id.homeFragment, R.id.dashboardFragment, R.id.notificationsFragment, R.id.containerInfoPendidikanFragment -> {
                    showBottomNav()
                }
                else -> {
                    hideBottomNav()
                }
            }
        }
    }

    fun showBottomNav(duration: Int = 400) {
        if (binding.bnMenu.visibility == View.VISIBLE) return
        binding.bnMenu.visibility = View.VISIBLE
        val animate = TranslateAnimation(0f, 0f, binding.bnMenu.height.toFloat(), 0f)
        animate.duration = duration.toLong()
        binding.bnMenu.startAnimation(animate)
    }

    private fun hideBottomNav(duration: Int = 400) {
        if (binding.bnMenu.visibility == View.GONE) return
        val animate = TranslateAnimation(0f, 0f, 0f, binding.bnMenu.height.toFloat())
        animate.duration = duration.toLong()
        binding.bnMenu.startAnimation(animate)
        binding.bnMenu.visibility = View.GONE
    }

}
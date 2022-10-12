package com.example.connect.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentMainAppBinding


class MainAppFragment : Fragment() {

    private lateinit var binding: FragmentMainAppBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_app, container, false)
//
//        val fragmentList = arrayListOf(
//            HomeFragment(),
//            DashboardFragment(),
//            LayananFragment()
//        )

        val navView = binding.navView
//        val container = binding.container
//        val adapter = AdapterBottomMenuViewPager(
//            fragmentList,
//            requireActivity().supportFragmentManager,
//            lifecycle
//        )


//        navView.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.navigation_home -> {
//
//                }
//                R.id.navigation_dashboard -> {
//                    container.findNavController().navigate(MainAppFragmentDirections.actionMainAppFragmentToDashboardFragment())
//                }
//                R.id.navigation_notifications -> {
//                }
//            }
//            true
//        }
/*
        binding.viewPager2.adapter = adapter
        viewPager.isUserInputEnabled = false
                */

//        val navController = requireActivity().findNavController(R.id.view_pager_2)

        binding.navView.setupWithNavController(this.findNavController())
//
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(
            R.id.fragment_con
        ) as NavHostFragment

        navController = navHostFragment.navController

        binding.navView.setupWithNavController(navController)

        return binding.root

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

}
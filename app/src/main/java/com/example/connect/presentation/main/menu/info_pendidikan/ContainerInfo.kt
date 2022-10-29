package com.example.connect.presentation.main.menu.info_pendidikan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.connect.R
import com.example.connect.databinding.ContainerInfoPendidikanFragmentBinding
import com.example.connect.presentation.main.menu.info_pendidikan.info.InfoUserFragment
import com.example.connect.presentation.main.menu.info_pendidikan.info.InfoUserViewModel
import com.example.connect.presentation.main.menu.info_pendidikan.pendidikan.PendidikanUserFragment
import com.example.connect.utilites.TabAdapter
import com.example.connect.utilites.app.SharedPreferences
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject
@AndroidEntryPoint
class ContainerInfo : Fragment() {
    private val viewModel: InfoUserViewModel by viewModels()

    @Inject
    lateinit var preferences: SharedPreferences
    val name = arrayOf(
        "Info",
        "Pendidikan"
    )

    lateinit var binding: ContainerInfoPendidikanFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.container_info_pendidikan_fragment,
            container,
            false
        )

        viewModel.getProfile(preferences.getIdUser())

        binding.menu.include12.root.visibility = View.GONE

        val fragmentList = arrayListOf(
            InfoUserFragment(),
            PendidikanUserFragment()
        )

        val tabLayout = binding.root.tabLayoutHome
        val viewPager = binding.root.viewPagerHome


        val adapter = TabAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        viewPager.adapter = adapter

        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            tab.text = name[position]
        }.attach()

        return binding.root
    }


}
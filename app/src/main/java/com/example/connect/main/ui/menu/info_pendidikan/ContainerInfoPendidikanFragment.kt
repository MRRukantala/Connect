package com.example.connect.main.ui.menu.info_pendidikan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.connect.R
import com.example.connect.databinding.ContainerInfoPendidikanFragmentBinding
import com.example.connect.main.ui.menu.info_pendidikan.info.InfoUserFragment
import com.example.connect.main.ui.menu.info_pendidikan.pendidikan.PendidikanUserFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.view.*

class ContainerInfoPendidikanFragment : Fragment() {

    companion object {
        fun newInstance() = ContainerInfoPendidikanFragment()
    }

    val name = arrayOf(
        "Info",
        "Pendidikan"
    )

    private lateinit var viewModel: ContainerInfoPendidikanViewModel
    lateinit var binding : ContainerInfoPendidikanFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.container_info_pendidikan_fragment, container, false)

        val fragmentList = arrayListOf(
            InfoUserFragment(),
            PendidikanUserFragment()
        )

        val tabLayout = binding.root.tabLayoutHome
        val viewPager =  binding.root.viewPagerHome


        val adapter = InfoPendidikanTabAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        viewPager.adapter = adapter

        TabLayoutMediator(
            tabLayout,
            viewPager
        ) {
            tab, position ->
            tab.text = name[position]
        }.attach()


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContainerInfoPendidikanViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
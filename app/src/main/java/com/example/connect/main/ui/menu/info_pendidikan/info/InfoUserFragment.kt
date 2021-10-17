package com.example.connect.main.ui.menu.info_pendidikan.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.InfoUserFragmentBinding

class InfoUserFragment : Fragment() {

    lateinit var binding: InfoUserFragmentBinding

    companion object {
        fun newInstance() = InfoUserFragment()
    }

    private lateinit var viewModel: InfoUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.info_user_fragment, container, false)
        binding.apply {
            buttonAbout.setOnClickListener {
                findNavController().navigate(R.id.action_containerInfoPendidikanFragment_to_itemListDialogFragment)
            }
            button5.setOnClickListener {
                findNavController().navigate(R.id.action_containerInfoPendidikanFragment_to_editInfoUserFragment)
            }
            logout.setOnClickListener {
                findNavController().setGraph(R.navigation.navigation)
            }
        }


        return binding.root
    }


}
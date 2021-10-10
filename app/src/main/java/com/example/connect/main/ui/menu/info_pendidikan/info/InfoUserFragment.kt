package com.example.connect.main.ui.menu.info_pendidikan.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.InfoUserFragmentBinding
import com.example.connect.main.ui.menu.bottomsheet.ItemListDialogFragment
import com.example.connect.main.ui.menu.info_pendidikan.ContainerInfoPendidikanFragmentDirections

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

        val itemModalBottomSheet = ItemListDialogFragment()

        binding = DataBindingUtil.inflate(inflater, R.layout.info_user_fragment, container, false)

        binding.buttonAbout.setOnClickListener {
            findNavController().navigate(R.id.action_containerInfoPendidikanFragment_to_itemListDialogFragment)
        }

        binding.button5.setOnClickListener {
            findNavController().navigate(R.id.action_containerInfoPendidikanFragment_to_editInfoUserFragment)
        }

        binding.logout.setOnClickListener {
            findNavController().setGraph(R.navigation.navigation)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InfoUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
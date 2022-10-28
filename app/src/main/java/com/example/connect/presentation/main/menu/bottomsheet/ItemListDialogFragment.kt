package com.example.connect.presentation.main.menu.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentItemListDialogListDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemListDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentItemListDialogListDialogBinding

    private val menuNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_item_list_dialog_list_dialog,
            container,
            false
        )

        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false)

        binding.linearLayout1.setOnClickListener {
            menuNavigation?.navigate(ItemListDialogFragmentDirections.actionItemListDialogFragmentToSaranNKritikFragment())
        }

        binding.linearLayout2.setOnClickListener {
            menuNavigation?.navigate(ItemListDialogFragmentDirections.actionItemListDialogFragmentToKetentuanAppFragment2())
        }

        binding.linearLayout3.setOnClickListener {
            menuNavigation?.navigate(ItemListDialogFragmentDirections.actionItemListDialogFragmentToAboutAppFragment())
        }
        return binding.root
    }
}
package com.example.connect.presentation.main.ui.menu.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentItemListDialogListDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    ItemListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class ItemListDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentItemListDialogListDialogBinding


    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list_dialog_list_dialog, container, false)

        binding = FragmentItemListDialogListDialogBinding.inflate(inflater, container, false)

        binding.linearLayout1.setOnClickListener {
            findNavController().navigate(ItemListDialogFragmentDirections.actionItemListDialogFragmentToSaranNKritikFragment())
        }

        binding!!.linearLayout2.setOnClickListener {
            findNavController().navigate(ItemListDialogFragmentDirections.actionItemListDialogFragmentToKetentuanAppFragment2())
        }

        binding!!.linearLayout3.setOnClickListener {
            findNavController().navigate(ItemListDialogFragmentDirections.actionItemListDialogFragmentToAboutAppFragment())
        }




        return binding.root

    }

}
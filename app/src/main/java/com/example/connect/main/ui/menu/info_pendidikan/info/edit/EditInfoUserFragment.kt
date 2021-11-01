package com.example.connect.main.ui.menu.info_pendidikan.info.edit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.EditInfoUserFragmentBinding

class EditInfoUserFragment : Fragment() {

    lateinit var binding: EditInfoUserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application

        binding =
            DataBindingUtil.inflate(inflater, R.layout.edit_info_user_fragment, container, false)
        binding.lifecycleOwner = this

        val data = EditInfoUserFragmentArgs.fromBundle(requireArguments()).dataHold
        val viewModelFactory = EditInfoUserViewModelFactory(
            requireActivity()
                .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                .getInt("id", -1),
            requireActivity()
                .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                .getString("token", "").toString(),
            application,
            data
        )

        binding.binding = ViewModelProvider(this, viewModelFactory).get(EditInfoUserViewModel::class.java)
        binding.include9.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}
package com.example.connect.main.ui.menu.info_pendidikan.info.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.connect.R
import com.example.connect.databinding.EditInfoUserFragmentBinding

class EditInfoUserFragment : Fragment() {

    lateinit var binding: EditInfoUserFragmentBinding

    companion object {
        fun newInstance() = EditInfoUserFragment()
    }

    private lateinit var viewModel: EditInfoUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.edit_info_user_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditInfoUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
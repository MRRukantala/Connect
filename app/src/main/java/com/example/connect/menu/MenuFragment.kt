package com.example.connect.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.MenuFragmentBinding

class MenuFragment : Fragment() {

    private lateinit var binding: MenuFragmentBinding

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container, false)

        binding.button.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToSignFragment())
        }

        binding.textView4.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToLoginFragment())
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.example.connect.main.ui.menu.info_pendidikan.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.MainActivity
import com.example.connect.R
import com.example.connect.databinding.InfoUserFragmentBinding
import com.example.connect.main.ui.menu.info_pendidikan.ContainerInfoDirections
import com.example.connect.main.ui.menu.info_pendidikan.info.edit.EditInfoUserViewModelFactory
import com.example.connect.splash.SplashScreenFragmentDirections

class InfoUserFragment : Fragment() {

    lateinit var binding: InfoUserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.info_user_fragment, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val factory =
            InfoUserViewModelFactory(
                requireActivity()
                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                    .getInt("id", -1),
                requireActivity()
                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                    .getString("token", "").toString(),
                application
            )

        val viewModel = ViewModelProvider(this, factory).get(InfoUserViewModel::class.java)
        binding.viewModel = viewModel

        binding.apply {
        }

        binding.apply {
            buttonAbout.setOnClickListener {
                findNavController().navigate(R.id.action_containerInfoPendidikanFragment_to_itemListDialogFragment)
            }
            button5.setOnClickListener {
                findNavController().navigate(ContainerInfoDirections.actionContainerInfoPendidikanFragmentToEditInfoUserFragment(viewModel.properties.value!!.data[0]))
            }
            logout.setOnClickListener {
                keluar()
            }
        }
        return binding.root
    }

    private fun keluar() {
        requireActivity().getSharedPreferences("my_data_pref", Context.MODE_PRIVATE).edit().clear()
            .commit()
        SplashScreenFragmentDirections.actionSplashScreenFragmentToMenuFragment()
//        SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()
        toMainActivity()
        requireActivity().finish()
    }

    fun toMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }


}
package com.example.connect.main.ui.menu.info_pendidikan.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.MainActivity
import com.example.connect.R
import com.example.connect.databinding.InfoUserFragmentBinding
import com.example.connect.splash.SplashScreenFragmentDirections

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
                keluar()
            }
        }
        return binding.root
    }

    private fun keluar(){
        requireActivity().getSharedPreferences("my_data_pref", Context.MODE_PRIVATE).edit().clear().commit()
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
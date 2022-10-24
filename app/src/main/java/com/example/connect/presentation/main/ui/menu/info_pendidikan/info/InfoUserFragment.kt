package com.example.connect.presentation.main.ui.menu.info_pendidikan.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.connect.MainActivity
import com.example.connect.R
import com.example.connect.databinding.InfoUserFragmentBinding
import com.example.connect.presentation.main.ui.menu.info_pendidikan.ContainerInfoDirections
import com.example.connect.presentation.splash.SplashScreenFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
@AndroidEntryPoint
class InfoUserFragment : Fragment() {

    lateinit var binding: InfoUserFragmentBinding
    private val viewModel: InfoUserViewModelTerbaru by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = InfoUserFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

//        val application = requireNotNull(activity).application
//        val factory =
//            InfoUserViewModelFactory(
//                requireActivity()
//                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//                    .getInt("id", -1),
//                requireActivity()
//                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//                    .getString("token", "").toString(),
//                application
//            )
//
//        val viewModel = ViewModelProvider(this, factory).get(InfoUserViewModel::class.java)
//        binding.viewModel = viewModel

        binding.apply {
            buttonAbout.setOnClickListener {
                findNavController().navigate(R.id.action_containerInfoPendidikanFragment_to_itemListDialogFragment)
            }
            button5.setOnClickListener {
//                findNavController().navigate(
//                    ContainerInfoDirections.actionContainerInfoPendidikanFragmentToEditInfoUserFragment(
//                        viewModel.properties.value!!.data[0]
//                    )
//                )
            }
            logout.setOnClickListener {
                keluar()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfile(52)
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state ->handleState(state) }
    }

    private fun handleState(state: InfoUserViewModelState) {

        when(state){
            is InfoUserViewModelState.Loading ->{}
            is InfoUserViewModelState.Success ->{}
        }

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
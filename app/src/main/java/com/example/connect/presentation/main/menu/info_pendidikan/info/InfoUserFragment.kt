package com.example.connect.presentation.main.ui.menu.info_pendidikan.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.connect.MainActivity
import com.example.connect.R
import com.example.connect.databinding.InfoUserFragmentBinding
import com.example.connect.presentation.main.menu.info_pendidikan.ContainerInfoDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class InfoUserFragment : Fragment() {

    lateinit var binding: InfoUserFragmentBinding
    private val viewModel: InfoUserViewModel by activityViewModels()

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
                findNavController().navigate(
                    ContainerInfoDirections.actionContainerInfoPendidikanFragmentToEditInfoUserFragment(

                    )
                )
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
            .onEach { state -> handleState(state) }
    }

    private fun handleState(state: InfoUserViewModelState) {

        when (state) {
            is InfoUserViewModelState.Loading -> {
                Log.v("DATA", "loading")
                Toast.makeText(activity, "LOADING", Toast.LENGTH_LONG).show()
            }
            is InfoUserViewModelState.Success -> {
                Log.v("DATA", "Sukses")
                Toast.makeText(activity, "SUKSES", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }

    }

    private fun keluar() {
        requireActivity().getSharedPreferences("my_data_pref", Context.MODE_PRIVATE).edit().clear()
            .commit()
        toMainActivity()
        requireActivity().finish()
    }

    fun toMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }


}
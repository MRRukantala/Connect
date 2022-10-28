package com.example.connect.presentation.main.menu.info_pendidikan.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.connect.R
import com.example.connect.databinding.InfoUserFragmentBinding
import com.example.connect.presentation.main.ContainerMainFragmentDirections
import com.example.connect.presentation.main.menu.info_pendidikan.ContainerInfoDirections
import com.example.connect.utilites.app.SharedPreferences
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class InfoUserFragment : Fragment() {

    @Inject
    lateinit var pref: SharedPreferences

    private val appNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_main)
    }

    private val menuNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    lateinit var binding: InfoUserFragmentBinding
    private val viewModel: InfoUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = InfoUserFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.apply {
            buttonAbout.setOnClickListener {
                menuNavigation?.navigate(R.id.action_containerInfo_to_itemListDialogFragment)
            }
            button5.setOnClickListener {
                menuNavigation?.navigate(ContainerInfoDirections.actionContainerInfoToEditInfoUserFragment(52))
            }
            logout.setOnClickListener {
                keluar()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProfile(pref.getIdUser())
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: InfoUserViewModelState) {

        when (state) {
            is InfoUserViewModelState.Loading -> {
                binding.msvProfile.viewState = MultiStateView.ViewState.LOADING
            }
            is InfoUserViewModelState.Success -> {
                binding.msvProfile.viewState = MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }

    }

    private fun keluar() {
        pref.clear()
        viewModelStore.clear()
        if (pref.getToken().isEmpty()) {
            appNavigation?.navigate(ContainerMainFragmentDirections.actionContainerMainFragmentToContainerAuthFragment2())
        }
    }
}
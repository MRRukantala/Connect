package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.PendidikanUserFragmentBinding
import com.example.connect.presentation.main.ui.home.tablayout.agenda.AgendaState
import com.example.connect.presentation.main.ui.menu.info_pendidikan.ContainerInfoDirections
import com.example.connect.presentation.main.ui.menu.info_pendidikan.info.InfoUserViewModelState
import com.example.connect.presentation.main.ui.menu.info_pendidikan.info.InfoUserViewModelTerbaru
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PendidikanUserFragment : Fragment() {

    lateinit var binding: PendidikanUserFragmentBinding
    
    private val viewModel: InfoUserViewModelTerbaru by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            PendidikanUserFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        
        binding.fabNews.setOnClickListener {

        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProfile(52)
        observe()

        binding.rv.adapter = PendidikanAdapter(

            PendidikanAdapter.OnclickListener{
                runCatching {
                    findNavController().navigate(ContainerInfoDirections.actionContainerInfoPendidikanFragmentToFormPendidikanFragment(it,1))
                }
            }
        )




    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
    }

    private fun handleState(state: InfoUserViewModelState) {

        when (state) {
            is InfoUserViewModelState.Loading -> {
                Log.v("PENDIDIKAN", "loading")
            }
            is InfoUserViewModelState.Success -> {
                Log.v("PENDIDIKAN", state.infoUserEntity.get(0).listPendidikan.toString())

                binding.fabNews.setOnClickListener {
                    findNavController().navigate(ContainerInfoDirections.actionContainerInfoPendidikanFragmentToFormPendidikanFragment(state.infoUserEntity.get(0).listPendidikan.get(0),0))
                }
            }

            else -> {}
        }



    }
}
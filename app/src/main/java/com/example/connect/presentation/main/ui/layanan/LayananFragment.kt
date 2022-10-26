package com.example.connect.presentation.main.ui.layanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LayananFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    private val viewModel: LayananViewModelTerbaru by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

//        val application = requireNotNull(activity).application
//        val factory = LayananViewModelFactory(
//            requireActivity()
//                .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//                .getString("token", "").toString(), application
//        )

//        val viewModel = ViewModelProvider(this, factory).get(LayananViewModel::class.java)
//        binding.viewModel = viewModel

        binding.recyclerView2.adapter = LayananAdapter(
            LayananAdapter.OnclickListener {
                findNavController().navigate(LayananFragmentDirections.actionNotificationsFragmentToDetailArtikelMarOIFragment())
            }
        )

//        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, {
//            if (null != it) {
//                this.findNavController().navigate(
//                    LayananFragmentDirections.actionNotificationsFragmentToDetailArtikelMarOIFragment(
//                        it
//                    )
//                )
//                viewModel.displayNewsDetailsCompelete()
//            }
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllLayanan()
        observe()


    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: LayananState) {
        when (state) {
            is LayananState.Loading -> {}
            is LayananState.Success -> {}
        }
    }

}
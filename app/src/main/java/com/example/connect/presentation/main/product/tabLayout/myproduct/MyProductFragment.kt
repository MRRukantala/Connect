package com.example.connect.presentation.main.product.tabLayout.myproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.connect.R
import com.example.connect.databinding.MyProductFragmentBinding
import com.example.connect.presentation.main.menu.info_pendidikan.info.InfoUserViewModel
import com.example.connect.presentation.main.menu.info_pendidikan.info.InfoUserViewModelState
import com.example.connect.presentation.main.product.ProdukFragmentDirections
import com.example.connect.utilites.app.SharedPreferences
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MyProductFragment : Fragment() {

    @Inject
    lateinit var pref: SharedPreferences

    lateinit var binding: MyProductFragmentBinding
    private val viewModel: MyProductViewModel by activityViewModels()
    private val viewModelProfile: InfoUserViewModel by viewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MyProductFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllProductByUser(pref.getIdUser())
        viewModelProfile.getProfile(pref.getIdUser())
        binding.viewModel = viewModel

        observe()
        binding.recyclerViewMyProduk.adapter = MyProductAdapter(
            MyProductAdapter.OnclickListener {
                mainNavigation?.navigate(
                    ProdukFragmentDirections.actionProdukFragmentToDetailProductFragment(
                        it.id
                    )
                )
            }
        )
    }


    private fun checkWa(wa: String): Boolean {
        var flag = true

        if (wa.isEmpty() == true) {
            Toast.makeText(
                requireContext(),
                "Maaf Anda harus mengisi no wa dulu",
                Toast.LENGTH_LONG
            ).show()
            flag = false
        }
        if (wa.length < 10) {
            Toast.makeText(requireContext(), "Pastikan No Hp Anda Benar", Toast.LENGTH_LONG).show()
            flag = false
        }
        if (wa.get(0) != '+' && wa.get(1) != '6' && wa.get(2) != '2') {
            Toast.makeText(requireContext(), "Pastikan No Hp Berformat +62", Toast.LENGTH_LONG)
                .show()
            flag = false
        }

        return flag
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
        viewModelProfile.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleStateProf(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleStateProf(state: InfoUserViewModelState) {

        when (state) {
            is InfoUserViewModelState.Loading -> {
            }
            is InfoUserViewModelState.Success -> {
                binding.fabNews.setOnClickListener {
                    if (checkWa(state.infoUserEntity.get(0).nomerHp) == true) {
                        mainNavigation?.navigate(ProdukFragmentDirections.actionProdukFragmentToAddMyProdukFragment())
                    }
                }
            }
            else -> {}
        }
    }

    private fun handleState(state: MyProductState) {

        when (state) {
            is MyProductState.Loading -> {
                binding.msvListProduct.viewState = MultiStateView.ViewState.LOADING
            }
            is MyProductState.Success -> {
                binding.msvListProduct.viewState =
                    if (state.myProductEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                    else MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }
    }
}
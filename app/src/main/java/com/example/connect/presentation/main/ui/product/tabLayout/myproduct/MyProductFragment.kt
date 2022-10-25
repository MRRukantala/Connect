package com.example.connect.presentation.main.ui.product.tabLayout.myproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.MyProductFragmentBinding
import com.example.connect.presentation.main.ui.product.DashboardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@AndroidEntryPoint
class MyProductFragment : Fragment() {

    lateinit var binding: MyProductFragmentBinding
    private val viewModel: MyProductViewModelTerbaru by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MyProductFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

//        val token = requireActivity()
//            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//            .getString("token", "").toString()
//        val id = requireActivity()
//            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//            .getInt("id", -1)
//
//        val application = requireNotNull(activity).application
//        val factory =
//            MyProductViewModelFactory(
//                token,
//                id,
//                application
//            )


//        val viewModel = ViewModelProvider(this, factory).get(MyProductViewModel::class.java)
        binding.viewModel = viewModel

//        binding.apply {
//            viewModel.properties.observe(viewLifecycleOwner, {
//                if (null != it) {
//                    binding.empty.visibility = View.GONE
//                }
//            })
//        }

        binding.recyclerViewMyProduk.adapter = MyProductAdapter(
            MyProductAdapter.OnclickListener {
//                viewModel.displayNewsDetails(it)
            }
        )

        binding.fabNews.setOnClickListener{
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToAddMyProdukFragment())
        }
//
//        binding.fabNews.setOnClickListener {
//            viewModel.wa.observe(viewLifecycleOwner, {
//                if (null != it) {
//                    findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToAddMyProdukFragment())
//                } else if (it == "" && it == " ") {
//                    Toast.makeText(
//                        requireContext(),
//                        "Maaf Anda harus mengisi no wa dulu",
//                        Toast.LENGTH_LONG
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Maaf Anda harus mengisi no wa dulu",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            })
//
//
//        }
//
//        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, {
//            if (null != it) {
//                this.findNavController().navigate(
//                    DashboardFragmentDirections.actionDashboardFragmentToDetailProductFragment(it)
//                )
//                viewModel.displayNewsDetailsCompelete()
//            }
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllProductByUser(52)

        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: MyProductState) {

        when (state) {
            is MyProductState.Loading -> {}
            is MyProductState.Success -> {
                binding.recyclerViewMyProduk.isVisible = true
                binding.empty.isVisible = false
            }
        }

    }


}
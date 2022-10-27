package com.example.connect.presentation.main.menu.info_pendidikan.pendidikan.edit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.FormPendidikanFragmentBinding
import com.example.connect.domain.entity.PendidikanEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FormPendidikanFragment : Fragment() {

    lateinit var binding: FormPendidikanFragmentBinding
    private val viewModel: FormPendidikanViewModelTerbaru by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            FormPendidikanFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.include8.backImage.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataPendidikan = arguments?.getParcelable<PendidikanEntity>("data")
        Log.v("Data", dataPendidikan.toString())
        observe()
        binding.btnHapus.setOnClickListener {
            viewModel.delete(dataPendidikan?.id!!)
        }

    }

    private fun observe() {
        viewModel.stateDelete.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DeleteState) {

        when (state) {
            is DeleteState.Loading -> Log.v("DATA", "Loading")

            is DeleteState.Success -> {
                Log.v("DATA", "Sukses")
                Toast.makeText(activity, "SUKSES HAPUS" + state.deletePendidikanEntity.instansi, Toast.LENGTH_LONG).show()
            }

            else -> {}
        }


    }
}
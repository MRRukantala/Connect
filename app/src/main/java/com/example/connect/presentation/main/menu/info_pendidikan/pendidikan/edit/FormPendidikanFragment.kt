package com.example.connect.presentation.main.menu.info_pendidikan.pendidikan.edit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FormPendidikanFragmentBinding
import com.example.connect.domain.entity.PendidikanEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_news_fragment.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FormPendidikanFragment : Fragment() {

    lateinit var binding: FormPendidikanFragmentBinding
    private val viewModel: FormPendidikanViewModelTerbaru by viewModels()

    private val authNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment_authentication) }
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment_main) }
    private lateinit var etInstansi: EditText
    private lateinit var etJenjang: EditText
    private lateinit var etFakultas: EditText
    private lateinit var etJurusan: EditText
    private lateinit var etTahunMasuk: EditText
    private lateinit var etTahunKeluar: EditText

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.isEmpty() == true) {
                viewModel.setAllFieldNull()
            } else {
                viewModel.setAllField(
                    binding.textIntansi.editText?.text.toString(),
                    binding.textJenjang.editText?.text.toString(),
                    binding.textFakultas.editText?.text.toString(),
                    binding.textJurusan.editText?.text.toString(),
                    binding.textTahunMasuk.editText?.text.toString(),
                    binding.textTahunLulus.editText?.text.toString()

                )
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            FormPendidikanFragmentBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner


        binding.include8.backImage.setOnClickListener {
            findNavController().popBackStack()
        }
        etInstansi = binding.instansi
        etJenjang = binding.jenjang
        etFakultas = binding.fakultas
        etJurusan = binding.jurusan
        etTahunMasuk = binding.tahunMasuk
        etTahunKeluar = binding.tahunLulus



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataPendidikan = arguments?.getParcelable<PendidikanEntity>("data")
        Log.v("Data", dataPendidikan.toString())
        listOf(
            etInstansi, etJenjang, etFakultas,
            etJurusan, etTahunMasuk, etTahunKeluar
        )
        observe()
        if (arguments?.getInt("id") == 0) {
            binding.btnHapus.isVisible = false
            binding.btnSimpan.text = "Tambahkan"
            binding.btnSimpan.isEnabled = true
            binding.btnSimpan.setOnClickListener {
                viewModel.postPendidikan()
            }
        } else {
            binding.btnHapus.setOnClickListener {
                viewModel.delete(dataPendidikan?.id!!)
            }

        }


    }

    private fun observe() {
        viewModel.stateDelete.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)

        viewModel.statePost.flowWithLifecycle(lifecycle)
            .onEach { state ->
                postPendidikanHandleState(state)
            }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DeleteState) {

        when (state) {
            is DeleteState.Loading -> Log.v("DATA", "Loading")

            is DeleteState.Success -> {
                Log.v("DATA", "Sukses")
                Toast.makeText(
                    activity,
                    "SUKSES HAPUS" + state.deletePendidikanEntity.instansi,
                    Toast.LENGTH_LONG
                ).show()
            }

            else -> {}
        }
    }

    private fun postPendidikanHandleState(state: PostState) {
        when (state) {
            is PostState.LoadingPost -> Log.v("DATA", "LOADING")

            is PostState.SuccessPost -> {
                Log.v("DATA", "Sukses")
                Toast.makeText(
                    activity,
                    "SUKSES HAPUS",
                    Toast.LENGTH_LONG
                ).show()
            }

            is PostState.ErrorPost -> {}
            else -> {}
        }
    }
}
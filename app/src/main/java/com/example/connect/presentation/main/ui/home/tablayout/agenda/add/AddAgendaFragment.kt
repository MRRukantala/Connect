package com.example.connect.presentation.main.ui.home.tablayout.agenda.add

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentAddAgendaBinding
import com.example.connect.utilites.DatePickerHelper
import com.example.connect.utilites.TimePickerHelper
import kotlinx.android.synthetic.main.fragment_add_agenda.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

class AddAgendaFragment : Fragment() {

    lateinit var binding: FragmentAddAgendaBinding
    private val viewModel: AddAgendaViewModelTerbaru by viewModels()
    lateinit var datePicker: DatePickerHelper
    lateinit var timePicker: TimePickerHelper

    private lateinit var etTitle: EditText
    private lateinit var etLokasi: EditText
    private lateinit var etKonten: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAgendaBinding.inflate(inflater, container, false)

        datePicker = DatePickerHelper(requireActivity())
        timePicker = TimePickerHelper(requireContext(), true, false)


        binding.apply {
            textView17.setOnClickListener {
                showDatePickerDialog()
            }
            textView19.setOnClickListener {
                showTimePickerDialog()
            }

        }

        with(binding){
            etTitle = editText.editText!!
            etLokasi = editText2.editText!!
            etLokasi = editText3.editText!!
        }
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonUpload = binding.fabNews
        observe()

        binding.cancelImagePost.setOnClickListener {
            binding.apply {
                cardView6.visibility = View.GONE
                addImageAgenda.setImageURI(null)
                fabAddImage.text = "Tambahkan Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        }

        etTitle.setText(viewModel.nama.value)
        etKonten.setText(viewModel.konten.value)
        etLokasi.setText(viewModel.konten.value)

        buttonUpload.setOnClickListener {

        }

    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: AddAgendaDataState) {

        when(state){
            is AddAgendaDataState.Loading ->{}
            is AddAgendaDataState.Success ->{}
        }

    }

    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)
        timePicker.showDialog(h, m, object : TimePickerHelper.Callback {
            override fun onTimeSelected(hourOfDay: Int, minute: Int) {
                val hourStr = if (hourOfDay < 10) "0${hourOfDay}" else "${hourOfDay}"
                val minuteStr = if (minute < 10) "0${minute}" else "${minute}"
                binding.textView19.text = "${hourStr} - ${minuteStr}"

                viewModel.setWaktuKegiatan("${hourStr} - ${minuteStr}")
            }
        })
    }


    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)

        datePicker.showDialog(d, m, y,
            object : DatePickerHelper.Callback {
                @SuppressLint("SetTextI18n")
                override fun onDateSelected(dayOfMonth: Int, month: Int, year: Int) {
                    val dayStr = if (dayOfMonth < 10) "0${dayOfMonth}" else "${dayOfMonth}"

                    val month = month + 1
                    val monthStr = if (month < 10) "0${month}" else "${month}"

                    binding.textView17.text = "${dayStr}${monthStr}${year}"

                    viewModel.setTanggal("${dayStr}${monthStr}${year}")

                }
            })
    }

}
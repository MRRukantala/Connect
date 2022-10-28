package com.example.connect.presentation.main.home.tablayout.agenda.add

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentAddAgendaBinding
import com.example.connect.utilites.DatePickerHelper
import com.example.connect.utilites.TimePickerHelper
import com.example.connect.utilites.app.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddAgendaFragment : Fragment() {
    @Inject
    lateinit var pref: SharedPreferences
    lateinit var binding: FragmentAddAgendaBinding
    private val viewModel: AddAgendaViewModelTerbaru by viewModels()
    lateinit var datePicker: DatePickerHelper
    lateinit var timePicker: TimePickerHelper

    private lateinit var etTitle: EditText
    private lateinit var etLokasi: EditText
    private lateinit var etKonten: EditText

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    var REQUEST_CODE = 101

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.isEmpty() == true) {
                viewModel.setAllFieldNull()
            } else {
                viewModel.setAllField(
                    binding.editText.editText?.text.toString(),
                    binding.editText2.editText?.text.toString(),
                    binding.editText3.editText?.text.toString()
                )
            }
        }

        override fun afterTextChanged(s: Editable?) {

            binding.fabNews.isEnabled = true

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAgendaBinding.inflate(inflater, container, false)
        viewModel.setAllFieldNull()

        binding.lifecycleOwner = viewLifecycleOwner

        datePicker = DatePickerHelper(requireActivity())
        timePicker = TimePickerHelper(requireContext(), true, false)


        binding.apply {
            textView17.setOnClickListener {
                showDatePickerDialog()
            }
            textView19.setOnClickListener {
                showTimePickerDialog()
            }
            fabAddImage.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    == PackageManager.PERMISSION_DENIED
                ) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.please_allow_permission),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    selectImageFromGallery()
                }
            }
        }



        with(binding) {
            etTitle = editText.editText!!
            etKonten = editText2.editText!!
            etLokasi = editText3.editText!!
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonUpload = binding.fabNews
        observe()
        listOf(etTitle, etKonten, etLokasi).forEach {
            it?.addTextChangedListener(textWatcher)
        }


        etTitle.setText(viewModel.nama.value)
        etKonten.setText(viewModel.konten.value)
        etLokasi.setText(viewModel.lokasi.value)

        viewModel.setStatus("1")
        Log.v("TOKEN", pref.getToken())

        binding.cancelImagePost.setOnClickListener {
            binding.apply {
                cardView6.visibility = View.GONE
                addImageAgenda.setImageURI(null)
                fabAddImage.text = "Tambahkan Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        }

        binding.fabNews.setOnClickListener {
            viewModel.postAgenda()
        }

    }


    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: AddAgendaDataState) {

        when (state) {
            is AddAgendaDataState.Loading -> {
                binding.iloading.root.visibility = View.VISIBLE
            }
            is AddAgendaDataState.Success -> {
                binding.iloading.root.visibility = View.GONE
                binding.iloadingsuccess.root.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    mainNavigation?.navigateUp()
                }, 2000)
            }
            else -> {}
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

    private fun selectImageFromGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        gallery.type = "image/*"
        startActivityForResult(gallery, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            var imageUri = data?.data

            var file = File(data?.data?.let { getRealPathFromURI(this.requireContext(), it) })

            val filePart = MultipartBody.Part.createFormData(
                "photo", file.name, RequestBody.create(
                    "image/*".toMediaTypeOrNull(), file
                )
            )

            binding.apply {
                viewModel.saveImage(filePart)
                addImageAgenda.setImageURI(imageUri)
                cardView6.visibility = View.VISIBLE
                fabAddImage.text = "Ganti Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_view_comfy_24)
            }
        } else {
            binding.apply {
                cardView6.visibility = View.GONE
                addImageAgenda.setImageURI(data?.data)

                cardView6.visibility = View.GONE
                addImageAgenda.setImageURI(null)
                viewModel.imageNull()
                fabAddImage.text = "Tambahkan Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        }
    }

    private fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } catch (e: Exception) {
            Log.e("", "getRealPathFromURI Exception : $e")
            ""
        } finally {
            cursor?.close()
        }
    }

}



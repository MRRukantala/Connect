package com.example.connect.main.ui.home.tablayout.agenda.add

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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentAddAgendaBinding
import com.example.connect.utilites.DatePickerHelper
import com.example.connect.utilites.TimePickerHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

class AddAgendaFragment : Fragment() {

    lateinit var binding: FragmentAddAgendaBinding

    lateinit var datePicker: DatePickerHelper
    lateinit var timePicker: TimePickerHelper

    var REQUEST_CODE = 101

    private val viewModel: AddAgendaViewModel by lazy {
        ViewModelProvider(this).get(AddAgendaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_agenda, container, false)

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
        return binding.root
    }

    private fun selectImageFromGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        gallery.type = "image/*"
        startActivityForResult(gallery, REQUEST_CODE)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonUpload = binding.fabNews
        val sharedPreferences = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)

        val token = sharedPreferences.getString("token", "")

        binding.cancelImagePost.setOnClickListener {
            binding.apply {
                cardView6.visibility = View.GONE
                addImageAgenda.setImageURI(null)
                fabAddImage.text = "Tambahkan Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        }


        viewModel.nama_kegiatan.observe(viewLifecycleOwner, {
            viewModel.postKirimanDataChanged()
        })
        viewModel.deskripsi_kegiatan.observe(viewLifecycleOwner, {
            viewModel.postKirimanDataChanged()
        })
        viewModel.lokasi_kegiatan.observe(viewLifecycleOwner, {
            viewModel.postKirimanDataChanged()
        })
        viewModel.tanggal_kegiatan.observe(viewLifecycleOwner, {
            viewModel.postKirimanDataChanged()

            if (it != null) {
                binding.textView17.setText(it.toString())
            }
        })
        viewModel.waktu_kegiatan.observe(viewLifecycleOwner, {
            viewModel.postKirimanDataChanged()

            if (it != null) {
                binding.textView19.setText(it.toString())
            }
        })
        viewModel.image.observe(viewLifecycleOwner, {
            viewModel.postKirimanDataChanged()
        })

        buttonUpload.setOnClickListener {
            viewModel.status(0)
            viewModel.posting(
                token = token ?: ""
            )
        }

        viewModel.state.observe(viewLifecycleOwner, {
            when (it) {
                AddAgendaState.SUCCESS -> {
                    Toast.makeText(context, "Success image upload", Toast.LENGTH_SHORT).show()
                    buttonUpload.isEnabled = true
                    findNavController().navigate(AddAgendaFragmentDirections.actionAddAgendaFragmentToLoadingAddAgendaFragment())
                }
                AddAgendaState.LOADING -> {
                    buttonUpload.isEnabled = false
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                    binding.loading.visibility = View.VISIBLE
                }
                AddAgendaState.ERROR -> {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(context, "Failure image upload", Toast.LENGTH_SHORT).show()
                    buttonUpload.isEnabled = true
                }
            }
        })

        viewModel.value.observe(viewLifecycleOwner, {
            if (it!!.isDataValid) {
                buttonUpload.isEnabled = true
            } else {
                buttonUpload.isEnabled = false
            }
        })


        val namaKegiatanafterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length!!.equals(0)) {
                    viewModel.namaKegiatanNull()
                } else {
                    viewModel.namaKegitan(p0.toString())
                }
            }
        }
        val rincianKegiatanTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length!!.equals(0)) {
                    viewModel.deskripsiKegiatanNull()
                } else {
                    viewModel.deskripsiKegiatan(p0.toString())
                }
            }

        }
        val lokasiKegiatanTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length!!.equals(0)) {
                    viewModel.lokasiKegiatanNull()
                } else {
                    viewModel.lokasiKegitan(p0.toString())
                }
            }

        }
        /**
        val tanggalKegiatanTextChangedListener = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
        if (p0?.length!!.equals(0)) {
        viewModel.tanggalKegiatanNull()
        } else {
        viewModel.tanggalKegitan(p0.toString())
        }
        }
        }
        val waktuKegiatanTextChangedListener = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun afterTextChanged(p0: Editable?) {
        if (p0?.length!!.equals(0)) {
        viewModel.waktuKegiatanNull()
        } else {
        viewModel.waktuKegiatan(p0.toString())
        }
        }
        }
         **/

        binding.editText.addTextChangedListener(namaKegiatanafterTextChangedListener)
        binding.editText2.addTextChangedListener(rincianKegiatanTextChangedListener)
        binding.editText3.addTextChangedListener(lokasiKegiatanTextChangedListener)

        /**
        binding.textView17.addTextChangedListener(tanggalKegiatanTextChangedListener)
        binding.textView19.addTextChangedListener(waktuKegiatanTextChangedListener)
         **/

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

                viewModel.waktuKegiatan("${hourStr} - ${minuteStr}")
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

                    viewModel.tanggalKegitan("${dayStr}${monthStr}${year}")

                }
            })
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
                viewModel.image(filePart)
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
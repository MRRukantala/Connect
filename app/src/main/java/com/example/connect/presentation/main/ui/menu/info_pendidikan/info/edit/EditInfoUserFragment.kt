package com.example.connect.presentation.main.ui.menu.info_pendidikan.info.edit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.EditInfoUserFragmentBinding
import com.example.connect.utilites.DatePickerHelper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class EditInfoUserFragment : Fragment() {

    lateinit var binding: EditInfoUserFragmentBinding
    private val REQUEST_CODE = 101
    lateinit var datePicker: DatePickerHelper


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding =
            DataBindingUtil.inflate(inflater, R.layout.edit_info_user_fragment, container, false)
        binding.lifecycleOwner = this


        datePicker = DatePickerHelper(requireActivity())

        val application = requireNotNull(activity).application

        val token = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
            .getString("token", "").toString()

//        val data = EditInfoUserFragmentArgs.fromBundle(requireArguments()).dataHold

//        val viewModelFactory = EditInfoUserViewModelFactory(
//            requireActivity()
//                .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//                .getInt("id", -1),
//            token,
//            application,
////            data
//        )



//        ViewModel = ViewModelProvider(this, viewModelFactory).get(EditInfoUserViewModel::class.java)
//        binding.binding = ViewModel

        binding.include9.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.circleImageView2.setOnClickListener {
            selectImageFromGallery()
        }

        binding.button5.setOnClickListener {


        }

        binding.rg.setOnCheckedChangeListener { radioGroup, i ->
            when(radioGroup.checkedRadioButtonId){
//                R.id.rl -> ViewModel!!.jenis_kelamin("Laki - Laki")
//                R.id.rp -> ViewModel!!.jenis_kelamin("Perempuan")
            }
        }

//        if(data.jenis_kelamin.equals("Laki - Laki")){
//            binding.rl.isChecked = true
//        } else if(data.jenis_kelamin.equals("Perempuan")){
//            binding.rp.isChecked = true
//        }


        binding.textView42.setOnClickListener {
            showDatePickerDialog()
        }

        val nimTextChangeListener = object  : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length!!.equals(0)){

                }  else {
//                    ViewModel!!.nim(p0.toString())
                }
            }

        }
        binding.textView36.addTextChangedListener(nimTextChangeListener)

        val noKontakChangeListener = object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length!!.equals(0)){

                }  else {
//                    ViewModel!!.wa(p0.toString())

                }
            }

        }
        binding.textView38.addTextChangedListener(noKontakChangeListener)

        val domisiliChangeListener = object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0?.length!!.equals(0)){

                }  else {
//                    ViewModel!!.domisili(p0.toString())

                }
            }

        }
        binding.textView40.addTextChangedListener(domisiliChangeListener)


//        ViewModel!!.state.observe(viewLifecycleOwner, {
//            when(it){
//                EditProfilState.SUCCESS -> {
//                    Toast.makeText(context, "Edit Data Berhasil", Toast.LENGTH_SHORT).show()
//                  }
//                EditProfilState.LOADING -> {
//                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
//                 }
//                EditProfilState.ERROR -> {
//                    Toast.makeText(context, "Edit Data Gagal", Toast.LENGTH_SHORT).show()
//                 }
//            }
//        })

        return binding.root
    }

    private fun selectImageFromGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        gallery.type = "image/*"
        startActivityForResult(gallery, REQUEST_CODE)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            var imageURI = data?.data

            var file = File(data?.data?.let { getRealPathFromURI(this.requireContext(), it) })

            val filePart = MultipartBody.Part.createFormData(
                "photo", file.name, RequestBody.create(
                    "image/*".toMediaTypeOrNull(), file
                )
            )
//            ViewModel!!.image(filePart)
            binding.circleImageView2.setImageURI(imageURI)
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

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)

        datePicker.showDialog(d, m, y,
            object : DatePickerHelper.Callback {
                @RequiresApi(Build.VERSION_CODES.O)
                @SuppressLint("SetTextI18n", "SimpleDateFormat")
                override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
                    val dayStr = if (dayOfMonth < 10) "0${dayOfMonth}" else "${dayOfMonth}"

                    val month = month + 1
                    val monthStr = if (month < 10) "0${month}" else "${month}"

                    val date = LocalDate.of(year, month, dayStr.toInt())
                    Log.v("DATE", date.toString())

                    val basic = date.format(DateTimeFormatter.BASIC_ISO_DATE)
//                    ViewModel!!.tanggal_lahir(basic)

                    binding.textView42.text = "${year} - ${monthStr} - ${dayStr}"
                }
            })
    }
}


package com.example.connect.presentation.main.ui.product.tabLayout.myproduct.add

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.AddMyProdukFragmentBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.Exception

class AddMyProdukFragment : Fragment() {

    lateinit var binding: AddMyProdukFragmentBinding
    private val REQUEST_CODE = 101

//    private val viewModel : AddMyProdukViewModel by lazy {
//        ViewModelProvider(this).get(AddMyProdukViewModel::class.java)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.add_my_produk_fragment, container, false)
        binding.include3.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.fabAddImage.setOnClickListener {
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
                ).show()
            } else {
                selectImageFromGallery()
            }
        }

        return binding.root
    }

    private fun selectImageFromGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        gallery.type = "image/*"
        startActivityForResult(gallery, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){

            var imageURI = data?.data

            var file = File(data?.data?.let { getRealPathFromURI(this.requireContext(), it) })

            val filePart = MultipartBody.Part.createFormData(
                "gambar", file.name, RequestBody.create(
                    "image/*".toMediaTypeOrNull(), file
                )
            )

//            binding.apply {
//                viewModel.image(filePart)
//                imgAddPost.setImageURI(imageURI)
//                cardAddPost.visibility = View.VISIBLE
//                fabAddImage.text = "Ganti Gambar"
//                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
//            }
        } else {
            binding.apply {
                cardAddPost.visibility = View.GONE
                imgAddPost.setImageURI(data?.data)

                cardAddPost.visibility = View.GONE
                imgAddPost.setImageURI(null)
//                viewModel.imageNull()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonUpload = binding.fabNews
        val sharedPreferences = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "")

        binding.cancelImagePost.setOnClickListener {
            binding.apply {
                cardAddPost.visibility = View.GONE
                imgAddPost.setImageURI(null)
//                viewModel.imageNull()
                fabAddImage.text = "Tambahkan Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        }

//        viewModel.image.observe(viewLifecycleOwner, {
//            viewModel.postProdukDataChanged()
//        })
//        viewModel.nama_product.observe(viewLifecycleOwner,{
//            viewModel.postProdukDataChanged()
//        })
//        viewModel.harga.observe(viewLifecycleOwner,{
//            viewModel.postProdukDataChanged()
//        })
//        viewModel.deskripsi.observe(viewLifecycleOwner,{
//            viewModel.postProdukDataChanged()
//        })
//
//        buttonUpload.setOnClickListener {
//
//            viewModel.posting(
//                token = token ?: ""
//            )
//        }


//        viewModel.state.observe(viewLifecycleOwner, {
//            when(it){
//                AddProdukState.SUCCESS -> {
//                    Toast.makeText(context, "Success image upload", Toast.LENGTH_SHORT).show()
//                    buttonUpload.isEnabled = true
//                    findNavController().navigate(AddMyProdukFragmentDirections.actionAddMyProdukFragmentToProsesAddMyProdukFragment())
//                }
//                AddProdukState.LOADING -> {
//                    buttonUpload.isEnabled = false
//                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
//                    binding.loading.visibility = View.VISIBLE
//                }
//                AddProdukState.ERROR -> {
//                    binding.loading.visibility = View.GONE
//                    Toast.makeText(context, "Failure image upload", Toast.LENGTH_SHORT).show()
//                    buttonUpload.isEnabled = true
//                }
//            }
//        })

//        viewModel.value.observe(viewLifecycleOwner, {
//            if(it!!.isDataValid){
//                buttonUpload.isEnabled = true
//            } else {
//                buttonUpload.isEnabled = false
//            }
//        })

//        val DeskripsiafterTextChangedListener = object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                if(p0?.length!!.equals(0)){
//                    viewModel.deskripsiProductNull()
//
//                }  else {
//                    viewModel.deskripsi(p0.toString())
//
//                }
//            }
//        }
//        val hargaProdukAfterChangedLister = object  : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                if(p0?.length!!.equals(0)){
//                    viewModel.hargaNull()
//                }  else {
//                    viewModel.harga("$p0".toInt())
//                }
//            }
//
//        }
//        val namaProukAfterChangedListener = object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                if(p0?.length!!.equals(0)){
//                    viewModel.namaProductNull()
//                }  else {
//                    viewModel.namaProduct(p0.toString())
//                }
//            }
//
//        }

//        binding.namaProduk.addTextChangedListener(namaProukAfterChangedListener)
//        binding.harga.addTextChangedListener(hargaProdukAfterChangedLister)
//        binding.Detail.addTextChangedListener(DeskripsiafterTextChangedListener)
    }


}
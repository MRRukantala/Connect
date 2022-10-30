package com.example.connect.presentation.main.home.tablayout.news.add

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
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.AddNewsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

@AndroidEntryPoint
class AddNewsFragment : Fragment() {

    lateinit var binding: AddNewsFragmentBinding
    private val viewModel: AddNewsViewModelTerbaru by viewModels()
    private val REQUEST_CODE = 101

    private lateinit var etKonten: EditText

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.isEmpty() == true) {
                viewModel.setAllFieldNull()
            } else {
                viewModel.setAllField(
                    binding.editText.text.toString()
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
        binding = AddNewsFragmentBinding.inflate(inflater, container, false)
        viewModel.setAllFieldNull()

        binding.lifecycleOwner = viewLifecycleOwner

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
                )
                    .show()
            } else {
                selectImageFromGallery()
            }
        }

        binding.include3.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        with(binding) {
            etKonten = editText

        }


        return binding.root
    }

    private fun selectImageFromGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        gallery.type = "image/*"
        startActivityForResult(gallery, REQUEST_CODE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        listOf(etKonten).forEach {
            it?.addTextChangedListener(textWatcher)

        }

        etKonten.setText(viewModel.konten.value)

        binding.cancelImagePost.setOnClickListener {
            binding.apply {
                cardAddPost.visibility = View.GONE
                imgAddPost.setImageURI(null)

                fabAddImage.text = "Tambahkan Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        }

        binding.fabNews.setOnClickListener {
            viewModel.postKiriman()
        }
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: PostNewsState) {
        when (state) {
            is PostNewsState.Loading -> {
                binding.iloading.root.visibility = View.VISIBLE
            }
            is PostNewsState.Success -> {
                mainNavigation?.popBackStack()
                Toast.makeText(requireContext(), "SUKSES", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            var imageURI = data?.data

            var file = File(data?.data?.let { getRealPathFromURI(this.requireContext(), it) })

            val filePart = MultipartBody.Part.createFormData(
                "gambar", file.name, RequestBody.create(
                    "image/*".toMediaTypeOrNull(), file
                )
            )

            viewModel.saveImage(filePart)


            binding.apply {

                imgAddPost.setImageURI(imageURI)
                cardAddPost.visibility = View.VISIBLE
                fabAddImage.text = "Ganti Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        } else {
            binding.apply {
                cardAddPost.visibility = View.GONE
                imgAddPost.setImageURI(data?.data)

                cardAddPost.visibility = View.GONE
                imgAddPost.setImageURI(null)
                viewModel.saveImageNull()
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

package com.example.connect.main.ui.home.news.add

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.AddNewsFragmentBinding
import com.example.connect.login.data.model.DataUser
import com.example.connect.login.data.model.UserResponse
import kotlinx.android.synthetic.main.fragment_sign.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File


class AddNewsFragment : Fragment() {

    lateinit var binding: AddNewsFragmentBinding

    private val REQUEST_CODE = 100
    private var imageUri: Uri? = null
    private lateinit var requestBody: RequestBody

    companion object {
        fun newInstance() = AddNewsFragment()
    }

    private lateinit var viewModel: AddNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_news_fragment, container, false)
        binding.fabAddImage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_DENIED
            ) {
                Toast.makeText(requireContext(), "Please allow permissions", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            gallery.setType("image/*")
            startActivityForResult(gallery, REQUEST_CODE)
            }
        }

        binding.include3.root.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.fabNews.setOnClickListener {
            findNavController().navigate(AddNewsFragmentDirections.actionAddNewsFragment2ToProsesAddingNewsFragment())
        }

        binding.include3.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AddNewsViewModel::class.java)


        val image = binding.imgAddPost
        val kontent = binding.editText
        val buttonUpload = binding.fabNews

        binding.cancelImagePost.setOnClickListener {
            binding.apply {
                cardAddPost.visibility = View.GONE
//                imgAddPost.setImageURI(null)
                imageUri = null
                viewModel.postKirimanDataChanged(imageUri.toString(), kontent.toString())
                fabAddImage.text = "Tambahkan Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        }



        viewModel.postKirimanState.observe(viewLifecycleOwner, Observer { postKirimanState ->
            if (postKirimanState == null) {
                return@Observer
            }

            postKirimanState.contentError?.let {
                kontent.error = getString(it)
            }

            buttonUpload.isEnabled = postKirimanState.isDataValid

        })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                Log.v("DATA KONTENT", imageUri.toString() + kontent.text.toString())
                viewModel.postKirimanDataChanged(imageUri.toString(), kontent.text.toString())
            }
        }
        kontent.addTextChangedListener(afterTextChangedListener)

        buttonUpload.setOnClickListener {
            viewModel.posting(loggedIn().data.token, requestBody!!, kontent.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            binding.apply {
                imageUri = data?.data

                var file = File(imageUri!!.path)
                val requestBodyFile: RequestBody =
                    RequestBody.create(MediaType.parse("image/*"), file.absolutePath.toString())
                requestBody = requestBodyFile


                cardAddPost.visibility = View.VISIBLE
                imgAddPost.setImageURI(imageUri)
                fabAddImage.text = "Ganti Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }
        } else {
            imageUri = data?.data
            binding.cardAddPost.visibility = View.GONE
            binding.imgAddPost.setImageURI(imageUri)
        }
    }

    fun loggedIn(): UserResponse {
        val sharedPreferences = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)

        val sharedPreferencesDataUser = DataUser(
            sharedPreferences.getInt("id", -1),
            sharedPreferences.getString("name", "").toString(),
            sharedPreferences.getString("email", "").toString(),
            sharedPreferences.getString("email_verified_at", "").toString(),
            sharedPreferences.getString("status", ""),
            sharedPreferences.getString("level", "").toString(),
            sharedPreferences.getString("created_at", "").toString(),
            sharedPreferences.getString("updated_at", "").toString()
        )

        val sharedPreferencesResponse = com.example.connect.login.data.model.response(
            sharedPreferences.getString("token", "").toString(),
            sharedPreferences.getString("token_type", "").toString(),
            sharedPreferencesDataUser
        )

        return UserResponse(
            sharedPreferencesResponse,
            "null"
        )
    }
}

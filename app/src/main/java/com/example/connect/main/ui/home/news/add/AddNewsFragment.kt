package com.example.connect.main.ui.home.news.add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.AddNewsFragmentBinding


class AddNewsFragment : Fragment() {

    lateinit var binding: AddNewsFragmentBinding

    private val REQUEST_CODE = 100
    private var imageUri: Uri? = null

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
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, REQUEST_CODE)
        }

        binding.include3.root.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.fabNews.setOnClickListener {
            findNavController().navigate(AddNewsFragmentDirections.actionAddNewsFragment2ToProsesAddingNewsFragment())
        }

        binding.cancelImagePost.setOnClickListener {
            binding.apply {
                cardAddPost.visibility = View.GONE
                imgAddPost.setImageURI(null)
                fabAddImage.text = "Tambahkan Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }

        }

        binding.include3.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNewsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            binding.apply {
                imageUri = data?.data
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
}
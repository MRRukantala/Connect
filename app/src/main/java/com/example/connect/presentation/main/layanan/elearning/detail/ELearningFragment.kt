package com.example.connect.presentation.main.layanan.elearning.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.connect.R

class ELearningFragment : Fragment() {

    companion object {
        fun newInstance() = ELearningFragment()
    }

    private lateinit var viewModel: ELearningViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_e_learning, container, false)
    }

}
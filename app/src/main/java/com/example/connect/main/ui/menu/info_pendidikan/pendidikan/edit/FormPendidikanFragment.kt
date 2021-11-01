package com.example.connect.main.ui.menu.info_pendidikan.pendidikan.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.connect.R

class FormPendidikanFragment : Fragment() {

    companion object {
        fun newInstance() = FormPendidikanFragment()
    }

    private lateinit var viewModel: FormPendidikanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.form_pendidikan_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormPendidikanViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
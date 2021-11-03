package com.example.connect.main.ui.menu.info_pendidikan.pendidikan.edit

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FormPendidikanFragmentBinding

class FormPendidikanFragment : Fragment() {

    lateinit var binding: FormPendidikanFragmentBinding
    lateinit var viewModel : FormPendidikanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.form_pendidikan_fragment, container, false)
        viewModel = ViewModelProvider(this).get(FormPendidikanViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        val data = FormPendidikanFragmentArgs.fromBundle(requireArguments()).pendidikanSelected
        val token =  requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
            .getString("token", "").toString()

        Log.v("ID", data!!.id.toString() + token)

        val instansitv = binding.instansi
        val jenjangtv = binding.jenjang
        val fakultastv = binding.fakultas
        val jurusantv = binding.jurusan
        val tahunmasuktv=binding.tahunMasuk
        val tahunlulustv = binding.tahunLulus

        viewModel.pendidikanForm.observe(viewLifecycleOwner,
            Observer {
                pendidikanFormState ->
                if(pendidikanFormState == null) {
                    return@Observer
            }

                binding.btnSimpan.isEnabled = pendidikanFormState.isDataValid
                pendidikanFormState.instansi?.let {
                        instansitv.error = getString(it)
                }
                pendidikanFormState.jenjang?.let {
                    jenjangtv.error = getString(it)
                }
                pendidikanFormState.fakultas?.let {
                    fakultastv.error = getString(it)
                }
                pendidikanFormState.jurusan?.let {
                    jurusantv.error = getString(it)
                }
                pendidikanFormState.tahunMasuk?.let {
                    tahunmasuktv.error = getString(it)
                }
                pendidikanFormState.tahunKeluar?.let {
                    tahunlulustv.error = getString(it)
                }
        })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.inputPendidikanDataChanged(
                    instansitv.text.toString(),
                    jenjangtv.text.toString(),
                    fakultastv.text.toString(),
                    jurusantv.text.toString(),
                    tahunmasuktv.text.toString(),
                    tahunlulustv.text.toString()
                )
            }
        }

        instansitv.addTextChangedListener(afterTextChangedListener)
        jenjangtv.addTextChangedListener(afterTextChangedListener)
        fakultastv.addTextChangedListener(afterTextChangedListener)
        jurusantv.addTextChangedListener(afterTextChangedListener)
        tahunmasuktv.addTextChangedListener(afterTextChangedListener)
        tahunlulustv.addTextChangedListener(afterTextChangedListener)

        binding.btnSimpan.setOnClickListener {
            viewModel.input(
               token,
                instansitv.text.toString(),
                jenjangtv.text.toString(),
                fakultastv.text.toString(),
                jurusantv.text.toString(),
                tahunmasuktv.text.toString(),
                tahunlulustv.text.toString()
            )
        }




        if(data == null){
            binding.btnHapus.visibility = View.GONE
        } else {
            binding.btnHapus.visibility = View.VISIBLE
            instansitv.setText(data.instansi)
            jenjangtv.setText(data.jenjang)
            fakultastv.setText(data.fakultas)
            jurusantv.setText(data.jurusan)
            tahunmasuktv.setText(data.tahun_masuk)
            tahunlulustv.setText(data.tahun_keluar)
        }

        binding.btnHapus.setOnClickListener {
            Log.v("PANGGIl", "KEPANGGIl")
            viewModel.delete(token, data!!.id)
        }

        viewModel.deleted.observe(viewLifecycleOwner, {
            if(null != it){
                findNavController().navigate(FormPendidikanFragmentDirections.actionFormPendidikanFragmentToContainerInfoPendidikanFragment())
            }
        })


        binding.include8.backImage.setOnClickListener {
            findNavController().popBackStack()
        }






        return binding.root
    }
}
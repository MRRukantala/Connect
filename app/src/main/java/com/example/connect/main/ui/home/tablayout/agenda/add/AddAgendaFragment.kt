package com.example.connect.main.ui.home.tablayout.agenda.add

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentAddAgendaBinding
import com.example.connect.utilites.DatePickerHelper
import com.example.connect.utilites.TimePickerHelper
import java.util.*

class AddAgendaFragment : Fragment() {

    lateinit var binding: FragmentAddAgendaBinding
    lateinit var datePicker: DatePickerHelper
    lateinit var timePicker: TimePickerHelper
    var REQUEST_CODE = 100
    private var imageUri: Uri? = null


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
            include3.backImage.setOnClickListener {
                findNavController().popBackStack()
            }
            fabNews.setOnClickListener {
                findNavController().navigate(AddAgendaFragmentDirections.actionAddAgendaFragmentToLoadingAddAgendaFragment())
            }
            fabAddImage.setOnClickListener {
                val gallery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, REQUEST_CODE)
            }
            binding.cancelImagePost.setOnClickListener {
                binding.apply {
                    cardView6.visibility = View.GONE
                    addImageAgenda.setImageURI(null)
                    fabAddImage.text = "Tambahkan Gambar"
                    fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
                }
            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val h = cal.get(Calendar.HOUR_OF_DAY)
        val m = cal.get(Calendar.MINUTE)
        timePicker.showDialog(h, m, object : TimePickerHelper.Callback {
            override fun onTimeSelected(hourOfDay: Int, minute: Int) {
                val hourStr = if (hourOfDay < 10) "0${hourOfDay}" else "${hourOfDay}"
                val minuteStr = if (minute < 10) "0${minute}" else "${minute}"
                binding.textView19.text = "${hourStr} : ${minuteStr}"
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

                    binding.textView17.text = "${dayStr} / ${monthStr} / ${year}"
                }

            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            binding.apply {
                imageUri = data?.data
                cardView6.visibility = View.VISIBLE
                addImageAgenda.setImageURI(imageUri)
                fabAddImage.text = "Ganti Gambar"
                fabAddImage.setIconResource(R.drawable.ic_baseline_swap_vert_24)
            }

        } else {
            imageUri = data?.data
            binding.cardView6.visibility = View.GONE
            binding.addImageAgenda.setImageURI(imageUri)

        }
    }
}
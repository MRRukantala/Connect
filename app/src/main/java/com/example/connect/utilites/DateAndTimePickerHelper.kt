package com.example.connect.utilites

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.*

class DatePickerHelper(context: Context, isSpinnerType: Boolean = false) {

    private lateinit var dialog: DatePickerDialog
    private var callback: Callback? = null

    private val listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
        callback?.onDateSelected(i, i2, i3)
    }

    init {
        val cal = Calendar.getInstance()

        dialog = DatePickerDialog(
            context, listener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun showDialog(
        dayOfMonth: Int,
        month: Int,
        year: Int,
        callback: Callback?
    ) {
        this.callback = callback
        dialog.datePicker.init(year, month, dayOfMonth, null)
        dialog.show()
    }

    fun setMinDate(minDate: Long){
        dialog.datePicker.minDate = minDate
    }

    fun setMaxDate(maxDate: Long){
        dialog.datePicker.maxDate = maxDate
    }

    interface  Callback {
        fun onDateSelected(dayOfMonth: Int, month: Int, year: Int)
    }

}

class TimePickerHelper(
    context: Context,
    is24HourView: Boolean,
    isSpinerType: Boolean = false
) {
    private var dialog: TimePickerDialog
    private var callback: TimePickerHelper.Callback? = null

    private val listener = TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
        callback?.onTimeSelected(hourOfDay, minute)
    }

    init {
//        val style = if (isSpinnerType) R.style.SpinnerTimePickerDialog else 0
        val cal = Calendar.getInstance()
        dialog = TimePickerDialog(
            context, listener,
            cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), is24HourView
        )
    }

    fun showDialog(hourOfDay: Int, minute: Int, callback: Callback?) {
        this.callback = callback
        dialog.updateTime(hourOfDay, minute)
        dialog.show()
    }

    interface Callback {
        fun onTimeSelected(hourOfDay: Int, minute: Int)
    }
}
package com.example.connect.utilites

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.icu.text.DateFormat.DAY
import android.icu.text.DateTimePatternGenerator.DAY
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.sql.Time
import java.text.NumberFormat
import java.util.*

fun isConnected(context: Context): Boolean {
    val connectionManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectionManager.activeNetworkInfo

    if (activeNetwork != null && activeNetwork.isConnectedOrConnecting == true) {
        return true
    } else {
        return false
    }
}

fun toastConnection(context: Context){
    Toast.makeText(context, "Internet tidak terhubung", Toast.LENGTH_SHORT).show()
}

private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

private fun currentDate(): Date {
    val calendar = Calendar.getInstance()
    return calendar.time
}

fun getTimeAgo(date: Date): String {
    var time = date.time
    if (time < 1000000000000L) {
        time *= 1000
    }

    val now = currentDate().time
    if (time > now || time <= 0) {
        return "in the future"
    }

    val diff = now - time
    return when {
        diff < MINUTE_MILLIS -> "moments ago"
        diff < 2 * MINUTE_MILLIS -> "a minute ago"
        diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} minutes ago"
        diff < 2 * HOUR_MILLIS -> "an hour ago"
        diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS} hours ago"
        diff < 48 * HOUR_MILLIS -> "yesterday"
        else -> "${diff / DAY_MILLIS} days ago"
    }
}

fun rupiah(number: Int): String{
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(number).toString()
}

class TabAdapter(
    list: ArrayList<androidx.fragment.app.Fragment>,
    fragmentManager: androidx.fragment.app.FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentList = list
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        return fragmentList[position]
    }
}

class AdapterBottomMenuViewPager(
    list: ArrayList<androidx.fragment.app.Fragment>,
    fragmentManager: androidx.fragment.app.FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val list = list

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        return list[position]
    }
}

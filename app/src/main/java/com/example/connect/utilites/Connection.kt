package com.example.connect.utilites

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

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
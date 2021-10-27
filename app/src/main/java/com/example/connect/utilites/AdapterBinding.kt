package com.example.connect.utilites

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.connect.R
import com.example.connect.main.ui.dashboard.modelproductumum.ProductUmum
import com.example.connect.main.ui.dashboard.store.ProductAdapter
import com.example.connect.main.ui.dashboard.store.umum.ProductUmumAdapter
import com.example.connect.main.ui.home.agenda.model.Agenda
import com.example.connect.main.ui.home.agenda.model.AgendaAdapter
import com.example.connect.main.ui.home.news.model.Post
import com.example.connect.main.ui.home.news.model.PostsAdapter
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

import java.util.*

@BindingAdapter("listPosts")
fun bindRecyclerViewListPosts(
    recyclerView: RecyclerView,
    data: List<Post>?
) {
    val adapter = recyclerView.adapter as PostsAdapter
    adapter.submitList(data)
}

@BindingAdapter("listAgendas")
fun bindRecyclerViewListAgenas(
    recyclerView: RecyclerView,
    data: List<Agenda>?
) {
    val adapter = recyclerView.adapter as AgendaAdapter
    adapter.submitList(data)
}

@BindingAdapter("listProductKhusus")
fun bindRecyclerViewListProductKhusus(
    recyclerView: RecyclerView,
    data: List<ProductUmum>?
) {
    val adapter = recyclerView.adapter as ProductAdapter
    adapter.submitList(data)
}

@BindingAdapter("listProductUmum")
fun bindRecyclerViewListProductUmum(
    recyclerView: RecyclerView,
    data: List<ProductUmum>?
) {
    val adapter = recyclerView.adapter as ProductUmumAdapter
    adapter.submitList(data)
}



@BindingAdapter("imageUrl")
fun imagePost(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(GET_PATH_IMAGE + imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)

            )
            .into(imgView)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SimpleDateFormat")
@BindingAdapter("time")
fun time(textViewTime: TextView, time: String) {
//    Log.v("GMANA SIH",    date.toString())
    textViewTime.setText(
        getTimeAgo(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(time)
        )
    )
}

@BindingAdapter("currerncy")
fun currency(textView: TextView, nominal: Int){
    textView.setText(rupiah(nominal))
}
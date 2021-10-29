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
import com.example.connect.main.ui.product.model.ProductModel
import com.example.connect.main.ui.product.tabLayout.productumum.ProductUmumAdapter
import com.example.connect.main.ui.home.tablayout.agenda.model.Agenda
import com.example.connect.main.ui.home.tablayout.agenda.AgendaAdapter
import com.example.connect.main.ui.home.tablayout.news.model.Post
import com.example.connect.main.ui.product.Adapter
import java.text.SimpleDateFormat

@BindingAdapter("listPosts")
fun bindRecyclerViewListPosts(
    recyclerView: RecyclerView,
    data: List<Post>?
) {
    val adapter = recyclerView.adapter as com.example.connect.main.ui.home.tablayout.news.Adapter
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
    data: List<ProductModel>?
) {
    val adapter = recyclerView.adapter as Adapter
    adapter.submitList(data)
}

@BindingAdapter("listProductUmum")
fun bindRecyclerViewListProductUmum(
    recyclerView: RecyclerView,
    data: List<ProductModel>?
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

@BindingAdapter("imageProfile")
fun imageProfile(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(GET_PATH_IMAGE + imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.avatar_1_raster)
            )
            .into(imgView)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SimpleDateFormat")
@BindingAdapter("time")
fun time(textViewTime: TextView, time: String) {
    Log.v("GMANA SIH",    time.toString())
    textViewTime.setText(
        getTimeAgo(
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)
        )
    )
}

@BindingAdapter("currerncy")
fun currency(textView: TextView, nominal: Int){
    textView.setText(rupiah(nominal))
}
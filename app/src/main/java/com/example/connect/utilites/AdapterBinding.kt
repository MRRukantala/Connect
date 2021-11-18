package com.example.connect.utilites

import android.annotation.SuppressLint
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.connect.R
import com.example.connect.database.SaveProductData
import com.example.connect.main.ui.home.tablayout.agenda.AgendaAdapter
import com.example.connect.main.ui.home.tablayout.agenda.model.Agenda
import com.example.connect.main.ui.home.tablayout.news.model.Post
import com.example.connect.main.ui.layanan.DataLayanan
import com.example.connect.main.ui.menu.info_pendidikan.MySubPendidikan
import com.example.connect.main.ui.product.Adapter
import com.example.connect.main.ui.product.model.ProductModel
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
    val adapter = recyclerView.adapter as com.example.connect.main.ui.product.tabLayout.productumum.Adapter
    adapter.submitList(data)
}

@BindingAdapter("listMyProduk")
fun bindRecyclerViewListMyProduk(
    recyclerView: RecyclerView,
    data: List<ProductModel>?
) {
    val adapter = recyclerView.adapter as com.example.connect.main.ui.product.tabLayout.myproduct.Adapter
    adapter.submitList(data)
}

@BindingAdapter("listLayanan")
fun bindRecyclerViewListLayanan(
    recyclerView: RecyclerView,
    data: List<DataLayanan>?
) {
    val adapter = recyclerView.adapter as com.example.connect.main.ui.layanan.Adapter
    adapter.submitList(data)
}

@BindingAdapter("listPendidikan")
fun bindRecyclerViewListPendidikan(
    recyclerView: RecyclerView,
    data: List<MySubPendidikan>?
) {
    val adapter = recyclerView.adapter as com.example.connect.main.ui.menu.info_pendidikan.pendidikan.Adapter
    adapter.submitList(data)
}

@BindingAdapter("listKeranjang")
fun bindRecyclerViewListKeranjang(
    recyclerView: RecyclerView,
    data: List<SaveProductData>?
) {
    val adapter = recyclerView.adapter as com.example.connect.main.ui.product.keranjang.Adapter
    adapter.submitList(data)
}

@BindingAdapter("listMyNews")
fun bindRecyclerViewMyNews(
    recyclerView: RecyclerView,
    data: List<Post>?
) {
    val adapter = recyclerView.adapter as com.example.connect.main.ui.home.tablayout.news.mynews.Adapter
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
    textViewTime.setText(
        getTimeAgo(
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)
        )
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SimpleDateFormat")
@BindingAdapter("timestampToDate")
fun timestampToDate(textViewTime: TextView, time: String) {
    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)

    textViewTime.setText(
        "Dibuat : " + SimpleDateFormat("yyyy-MMMM-dd").format(date).toString()
    )
}

@BindingAdapter("emptyornot")
fun emptyornot(textViewTime: TextView, value : String?){
    if(value == null || value == "" || value == " "){
        textViewTime.setText("Belum Ditambahkan")
    } else {
        textViewTime.setText(value.toString())
    }
}


@BindingAdapter("currerncy")
fun currency(textView: TextView, nominal: Int){
    textView.setText(rupiah(nominal))
}
package com.example.connect.utilites

import android.annotation.SuppressLint
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import com.example.connect.R
import com.example.connect.data.database.SaveProductData
import com.example.connect.domain.entity.*
import com.example.connect.presentation.main.home.tablayout.agenda.AgendaAdapter
import com.example.connect.presentation.main.home.tablayout.news.NewsAdapter
import com.example.connect.presentation.main.menu.info_pendidikan.pendidikan.PendidikanAdapter
import com.example.connect.presentation.main.product.ProductAdapter
import com.example.connect.presentation.main.product.tabLayout.myproduct.MyProductAdapter
import java.text.SimpleDateFormat

const val GET_PATH_IMAGE = "https://megha.desa-pintar.com/markol/public/storage/"

@BindingAdapter("listPosts")
fun bindRecyclerViewListPosts(
    recyclerView: RecyclerView,
    data: List<KirimanEntity>?
) {
    val adapter =
        recyclerView.adapter as com.example.connect.presentation.main.home.tablayout.news.NewsAdapter
    adapter.submitList(data)
}

@BindingAdapter("listAgendas")
fun bindRecyclerViewListAgenas(
    recyclerView: RecyclerView,
    data: List<AgendaEntity>?
) {
    val adapter = recyclerView.adapter as AgendaAdapter
    adapter.submitList(data)
}

@BindingAdapter("listPendidikan")
fun bindRecyclerViewListPendidikan(
    recyclerView: RecyclerView,
    data: List<PendidikanEntity>?
) {
    val adapter = recyclerView.adapter as PendidikanAdapter
    adapter.submitList(data)
}

@BindingAdapter("listProductKhusus")
fun bindRecyclerViewListProductKhusus(
    recyclerView: RecyclerView,
    data: List<ProductEntity>?
) {
    val adapter = recyclerView.adapter as ProductAdapter
    adapter.submitList(data)
}

@BindingAdapter("listProductUmum")
fun bindRecyclerViewListProductUmum(
    recyclerView: RecyclerView,
    data: List<ProductEntity>?
) {
    val adapter =
        recyclerView.adapter as com.example.connect.presentation.main.product.tabLayout.productumum.ProductUmumAdapter
    adapter.submitList(data)
}

@BindingAdapter("listMyProduk")
fun bindRecyclerViewListMyProduk(
    recyclerView: RecyclerView,
    data: List<ProductEntity>?
) {
    val adapter = recyclerView.adapter as MyProductAdapter
    adapter.submitList(data)
}

@BindingAdapter("listLayanan")
fun bindRecyclerViewListLayanan(
    recyclerView: RecyclerView,
    data: List<LayananEntity>?
) {
    val adapter =
        recyclerView.adapter as com.example.connect.presentation.main.layanan.LayananAdapter
    adapter.submitList(data)
}

//@BindingAdapter("listPendidikan")
//fun bindRecyclerViewListPendidikan(
//    recyclerView: RecyclerView,
//    data: List<PendidikanEntity>?
//) {
//    val adapter = recyclerView.adapter as
//    adapter.submitList(data)
//}

@BindingAdapter("listKeranjang")
fun bindRecyclerViewListKeranjang(
    recyclerView: RecyclerView,
    data: List<SaveProductData>?
) {
    val adapter =
        recyclerView.adapter as com.example.connect.presentation.main.product.keranjang.Adapter
    adapter.submitList(data)
}

@BindingAdapter("listMyNews")
fun bindRecyclerViewMyNews(
    recyclerView: RecyclerView,
    data: List<KirimanEntity>?
) {
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageUrl")
fun imagePost(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imageUrl = ImageRequest.Builder(imgView.context)
            .data("${GET_PATH_IMAGE}${it.toUri()}")
            .allowHardware(false)
            .build()
        imgView.load("${imageUrl.data}") {
            placeholder(R.drawable.loading_animation)
            this.error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("imageProfile")
fun imageProfile(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imageUrl = ImageRequest.Builder(imgView.context)
            .data("${GET_PATH_IMAGE}${it.toUri()}")
            .allowHardware(false)
            .build()
        imgView.load("${imageUrl.data}") {
            placeholder(R.drawable.loading_animation)
            this.error(R.drawable.avatar_1_raster)
        }
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
fun emptyornot(textViewTime: TextView, value: String?) {
    if (value == null || value == "" || value == " ") {
        textViewTime.setText("Belum Ditambahkan")
    } else {
        textViewTime.setText(value.toString())
    }
}


@BindingAdapter("currerncy")
fun currency(textView: TextView, nominal: Int) {
    textView.setText(rupiah(nominal))
}
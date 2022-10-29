package com.example.connect.data.database

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "saved_product_data")
data class SaveProductDataEntity(

    @PrimaryKey(autoGenerate = true)
    val idSaveProductData: Long = 0L,

    @ColumnInfo(name = "_id")
    var id: Int,

    @ColumnInfo(name = "_name")
    var name: String,

    @ColumnInfo(name = "_gambar")
    var gambar: String,

    @ColumnInfo(name = "harga")
    var harga: Int,

    @ColumnInfo(name = "_nama_produk")
    var nama_produk: String,

    @ColumnInfo(name = "deskripsi")
    var deskripsi: String,

    @ColumnInfo(name = "wa")
    var wa: String?,

    @ColumnInfo(name = "id_user")
    var id_user: Int
) {
    fun toItemKeranjangEntity() = ItemKeranjangEntity(
        idSaveProductData,
        id,
        name,
        gambar,
        harga,
        deskripsi,
        wa,
        id_user
    )
}

@Keep
@Parcelize
data class ItemKeranjangEntity(
    val idData: Long,
    val idProduct: Int,
    val namaProduct: String,
    val gambarProduct: String,
    val harga: Int,
    val deskripsi: String,
    val wa: String?,
    val idUser: Int
) : Parcelable

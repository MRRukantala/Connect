package com.example.connect.data.model.response


import com.example.connect.domain.entity.DetailProductEntity
import com.example.connect.domain.entity.ProductEntity
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("id_user")
    val idUser: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("level")
    val level: String? = "",
    @SerializedName("photo")
    val photo: String? = "",
    @SerializedName("gambar")
    val gambar: String? = "",
    @SerializedName("harga")
    val harga: Int? = 0,
    @SerializedName("nama_produk")
    val namaProduk: String? = "",
    @SerializedName("deskripsi")
    val deskripsi: String? = "",
    @SerializedName("wa")
    val wa: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = ""
) {
    fun toProductEntity() = ProductEntity(
        id ?: 0,
        namaProduk.orEmpty(),
        harga ?: 0,
        gambar.orEmpty(),
        createdAt.orEmpty()
    )

    fun toDetailProductEntity() = DetailProductEntity(

        id ?: 0,
        idUser ?: 0,
        deskripsi.orEmpty(),
        namaProduk.orEmpty(),
        harga ?: 0,
        gambar.orEmpty(),
        createdAt.orEmpty()

    )
}
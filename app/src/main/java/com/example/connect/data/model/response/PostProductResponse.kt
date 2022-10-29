package com.example.connect.data.model.response


import com.example.connect.domain.entity.PostProductEntity
import com.google.gson.annotations.SerializedName

data class PostProductResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("id_user")
    val idUser: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
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
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = ""
) {
    fun toPostProductEntity() = PostProductEntity(
        id ?: 0,
        idUser ?: 0,
        name.orEmpty(),
        photo.orEmpty(),
        gambar.orEmpty(),
        harga ?: 0,
        namaProduk.orEmpty(),
        deskripsi.orEmpty(),
        updatedAt.orEmpty(),
        createdAt.orEmpty()
    )
}
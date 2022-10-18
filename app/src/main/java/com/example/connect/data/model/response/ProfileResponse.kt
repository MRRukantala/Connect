package com.example.connect.data.model.response


import com.example.connect.domain.entity.PendidikanEntity
import com.example.connect.domain.entity.ProfileEntity
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("id_user")
    val idUser: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("nim")
    val nim: String? = "",
    @SerializedName("tgl_lahir")
    val tglLahir: String? = "",
    @SerializedName("wa")
    val wa: String? = "",
    @SerializedName("domisili")
    val domisili: String? = "",
    @SerializedName("photo")
    val photo: String? = "",
    @SerializedName("jenis_kelamin")
    val jenisKelamin: String? = "",
    @SerializedName("pendidikan")
    val pendidikan: List<PendidikanResponse?> = mutableListOf()
){
    fun toProfileEntity() =
        ProfileEntity(
            name.orEmpty(),
            wa.orEmpty(),
            domisili.orEmpty(),
            tglLahir.orEmpty(),
            jenisKelamin.orEmpty(),
            photo.orEmpty(),
            pendidikan.map { it!!.toPendidikanEntity() }
        )
}
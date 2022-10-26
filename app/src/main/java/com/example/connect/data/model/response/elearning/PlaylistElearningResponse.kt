package com.example.connect.data.model.response.elearning

import com.example.connect.domain.entity.elearning.PlaylistELearningEntity
import com.example.connect.domain.entity.elearning.VideoELearningEntity
import com.google.gson.annotations.SerializedName

data class PlaylistElearningResponse(
    @SerializedName("id_playlist") val idPlaylist: Int? = 0,
    @SerializedName("nama_playlist") val namaPlaylist: String? = "",
    @SerializedName("created_at") val dibuatPada: String? = "",
    @SerializedName("updated_at") val diubahPada: String? = ""
) {
    fun toPlaylistELearningEntity() = PlaylistELearningEntity(
        idPlaylist ?: 0,
        namaPlaylist.orEmpty(),
        dibuatPada.orEmpty(),
        diubahPada.orEmpty()
    )
}

data class VideoELearningResponse(
    @SerializedName("id_video") val idVideo: Int? = 0,
    @SerializedName("id_playlist") val idPlaylist: Int? = 0,
    @SerializedName("judul_video") val judulVideo: String? = "",
    @SerializedName("link_video") val linkVideo: String? = "",
    @SerializedName("created_at") val dibuatPada: String? = "",
    @SerializedName("updated_at") val diubahPada: String? = ""
) {
    fun toVideoELearningEntity() = VideoELearningEntity(
        idVideo ?: 0,
        idPlaylist ?: 0,
        judulVideo.orEmpty(),
        linkVideo.orEmpty(),
        dibuatPada.orEmpty(),
        diubahPada.orEmpty()
    )
}

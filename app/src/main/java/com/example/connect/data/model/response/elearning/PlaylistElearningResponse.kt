package com.example.connect.data.model.response.elearning

import com.example.connect.domain.entity.elearning.PlaylistELearningByIdEntity
import com.example.connect.domain.entity.elearning.PlaylistELearningEntity
import com.example.connect.domain.entity.elearning.VideoELearningByIdEntity
import com.example.connect.domain.entity.elearning.VideoELearningEntity
import com.google.gson.annotations.SerializedName

data class PlaylistElearningResponse(
    @SerializedName("id") val idPlaylist: Int? = 0,
    @SerializedName("nama") val namaPlaylist: String? = "",
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

data class PlaylistElearningByIdResponse(
    @SerializedName("id") val idPlaylist: Int? = 0,
    @SerializedName("nama") val namaPlaylist: String? = "",
    @SerializedName("created_at") val dibuatPada: String? = "",
    @SerializedName("updated_at") val diubahPada: String? = "",
    @SerializedName("video") val videos: List<VideoELearningByIdResponse>? = mutableListOf()
) {
    fun toPlaylistELearningByIdEntity() = PlaylistELearningByIdEntity(
        idPlaylist ?: 0,
        namaPlaylist.orEmpty(),
        dibuatPada.orEmpty(),
        diubahPada.orEmpty(),
        videos?.map { it.toVideoELearningByIdEntity() }?.reversed() ?: mutableListOf()
    )
}

data class VideoELearningByIdResponse(
    @SerializedName("id") val idVideo: Int? = 0,
    @SerializedName("id_playlist") val idPlaylist: Int? = 0,
    @SerializedName("judul") val judul: String? = "",
    @SerializedName("url") val linkVideo: String? = ""
) {
    fun toVideoELearningByIdEntity() = VideoELearningByIdEntity(
        idVideo ?: 0,
        idPlaylist ?: 0,
        judul.orEmpty(),
        linkVideo.orEmpty()
    )
}


data class VideoELearningResponse(
    @SerializedName("id") val idVideo: Int? = 0,
    @SerializedName("id_playlist") val idPlaylist: Int? = 0,
    @SerializedName("judul") val judulVideo: String? = "",
    @SerializedName("url") val linkVideo: String? = "",
    @SerializedName("created_at") val dibuatPada: String? = "",
    @SerializedName("updated_at") val diubahPada: String? = "",
    @SerializedName("playlist") val playlist: PlaylistElearningResponse?
) {
    fun toVideoELearningEntity() = VideoELearningEntity(
        idVideo ?: 0,
        idPlaylist ?: 0,
        judulVideo.orEmpty(),
        linkVideo.orEmpty(),
        dibuatPada.orEmpty(),
        diubahPada.orEmpty(),
        playlist?.toPlaylistELearningEntity() ?: PlaylistELearningEntity(0, "", "", "")
    )
}

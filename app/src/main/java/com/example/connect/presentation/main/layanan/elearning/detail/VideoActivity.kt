package com.example.connect.presentation.main.layanan.elearning.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.navArgs
import com.example.connect.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult

class VideoActivity : YouTubeBaseActivity() {
    private val args by navArgs<VideoActivityArgs>()
    val KEY = "AIzaSyAdZTUlHghweev8X97ULMFa1Wcw_3oudFA"

    lateinit var youtubePlayer: com.google.android.youtube.player.YouTubePlayerView
    lateinit var btnBack: View

    lateinit var youTubePlayerInit: com.google.android.youtube.player.YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val VIDEO_ID = args.data.linkVideo

        youtubePlayer = findViewById(R.id.yysda)

        youTubePlayerInit =
            object : com.google.android.youtube.player.YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: com.google.android.youtube.player.YouTubePlayer.Provider?,
                    p1: com.google.android.youtube.player.YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.loadVideo(VIDEO_ID.substringAfter("/watch?v=").substringBefore("&"))

                }

                override fun onInitializationFailure(
                    p0: com.google.android.youtube.player.YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(applicationContext, "Gagal", Toast.LENGTH_SHORT).show()
                }


            }
        youtubePlayer.initialize(KEY, youTubePlayerInit)
    }
}
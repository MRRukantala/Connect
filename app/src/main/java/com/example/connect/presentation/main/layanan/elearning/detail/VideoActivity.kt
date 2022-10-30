package com.example.connect.presentation.main.layanan.elearning.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.connect.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class VideoActivity : YouTubeBaseActivity() {
    val VIDEO_ID = "https://www.youtube.com/watch?v=W1qXi_ph0YE"
    val KEY = "AIzaSyAdZTUlHghweev8X97ULMFa1Wcw_3oudFA"

    lateinit var youtubePlayer: YouTubePlayerView

    lateinit var youTubePlayerInit: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        youtubePlayer = findViewById(R.id.yysda)
        youTubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(VIDEO_ID.substringAfter("/watch?v="))

            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Gagal", Toast.LENGTH_SHORT).show()
            }


        }
        youtubePlayer.initialize(KEY, youTubePlayerInit)
    }
}
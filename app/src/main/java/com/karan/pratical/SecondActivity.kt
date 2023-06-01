package com.karan.pratical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.karan.pratical.databinding.ActivityMainBinding
import com.karan.pratical.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var biding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(biding.root)

        intent
        var url: String? = intent.getStringExtra("artworkUrl100");
        var artistName: String? = intent.getStringExtra("artistName");
        var trackName: String? = intent.getStringExtra("trackName");

        Glide.with(applicationContext).load(url).into(biding.igUserImage);
        biding.txArtistName.text = artistName
        biding.txTrackName.text = trackName

    }
}
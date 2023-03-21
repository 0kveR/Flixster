package com.example.flixster

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class InfoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val title: TextView = findViewById(R.id.intent_title)
        val posterImage: ImageView = findViewById(R.id.intent_image)
        val description: TextView = findViewById(R.id.intent_description)
        val closeBtn: ImageView = findViewById(R.id.intent_close_btn)

        val text = intent.getStringExtra("title")
        title.text = text
        val posterImageURL = intent.getStringExtra("imageURL")
        if (posterImageURL != null) {
            Log.i("PosterURL", posterImageURL)
        } else  {
            Log.i("PosterURL", "null")
        }
        Glide.with(this).load(posterImageURL)
            .centerInside().into(posterImage)
        description.text = intent.getStringExtra("description")

        closeBtn.setOnClickListener { finish() }
    }
}
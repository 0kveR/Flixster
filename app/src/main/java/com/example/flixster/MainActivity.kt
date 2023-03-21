package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        fm.beginTransaction().replace(R.id.content1, MovieFragment()).commit()
        fm.beginTransaction().replace(R.id.content2, ShowFragment()).commit()
    }
}
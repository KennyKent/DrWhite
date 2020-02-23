package com.suonk.drwhite

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val button = findViewById<RelativeLayout>(R.id.main_activty_go_to_start_playing)
        button.setOnClickListener {
            startActivity(Intent(this, StartPlayingActivity::class.java))
        }
    }
}

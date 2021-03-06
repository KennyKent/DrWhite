package com.suonk.drwhite

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    val SPLASH_DISPLAY_LENGHT : Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            Handler().postDelayed({
                run {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }, SPLASH_DISPLAY_LENGHT)
        }
}

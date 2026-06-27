package com.example.fb10_app

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//variables
        val themeSwitch = findViewById<MaterialSwitch>(R.id.themeSwitch)
        val themeStatusText = findViewById<TextView>(R.id.themeStatusText)
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            themeSwitch.isChecked = true
            themeStatusText.text = "Dark Mode Active"
        } else {
            themeSwitch.isChecked = false
            themeStatusText.text = "Light Mode Active"
        }

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            //quick animation for fade in and fadeout
            val fadeOut = AlphaAnimation(1.0f, 0.0f).apply {
                duration = 200 // Time in milliseconds
                fillAfter = true
            }
            themeStatusText.startAnimation(fadeOut)

            //  Schedule theme change right after text fades out
            themeStatusText.postDelayed({
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }


                overrideActivityTransition(
                    OVERRIDE_TRANSITION_CLOSE,
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
            }, 200)
        }
    }
}

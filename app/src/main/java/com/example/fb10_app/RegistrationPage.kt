package com.example.fb10_app

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

class RegistrationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /* val registrationBtn = findViewById<Button>(R.id.btnRegister)
        val themeSwitch = findViewById<MaterialSwitch>(R.id.themeSwitch1)
        val mainLayout = findViewById<ConstraintLayout>(R.id.main)
        val txtFullName = findViewById<TextView>(R.id.textView3)
        val txtEmail = findViewById<TextView>(R.id.textView)
        val txtPassword = findViewById<TextView>(R.id.textView2)
        val txtConfirmPassword = findViewById<TextView>(R.id.textView5)
        val fullName = findViewById<EditText>(R.id.etName)
        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val confirmPassword = findViewById<EditText>(R.id.editTextTextPassword2)

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            registrationBtn.setBackgroundColor(Color.parseColor("#7B1FA2"))
            registrationBtn.setTextColor(Color.WHITE)
            mainLayout.setBackgroundColor(Color.parseColor("#0D1B2A")) // dark blue
            // textview only or the text for email and etc
            txtFullName.setTextColor(Color.WHITE)
            txtEmail.setTextColor(Color.WHITE)
            txtPassword.setTextColor(Color.WHITE)
            txtConfirmPassword.setTextColor(Color.WHITE)
            //edittext
            fullName.setBackgroundColor(Color.parseColor("#1E293B"))
            email.setBackgroundColor(Color.parseColor("#1E293B"))
            password.setBackgroundColor(Color.parseColor("#1E293B"))
            confirmPassword.setBackgroundColor(Color.parseColor("#1E293B"))

            fullName.setTextColor(Color.WHITE)
            email.setTextColor(Color.WHITE)
            password.setTextColor(Color.WHITE)
            confirmPassword.setTextColor(Color.WHITE)

            fullName.setHintTextColor(Color.GRAY)
            email.setHintTextColor(Color.GRAY)
            password.setHintTextColor(Color.GRAY)
            confirmPassword.setHintTextColor(Color.GRAY)

        }else{
            registrationBtn.setBackgroundColor(Color.parseColor("#FFD600")) // Yellow
            registrationBtn.setTextColor(Color.BLACK)
            mainLayout.setBackgroundResource(R.drawable.registration_bg)
//textview
            txtFullName.setTextColor(Color.BLACK)
            txtEmail.setTextColor(Color.BLACK)
            txtPassword.setTextColor(Color.BLACK)
            txtConfirmPassword.setTextColor(Color.BLACK)


        }
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }*/
    }
}
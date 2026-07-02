package com.example.fb10_app

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch

class RegistrationPage : AppCompatActivity() {
    private fun setupPasswordToggle(
        passwordField: EditText,
        confirmPasswordField: EditText
    ) {

        var isVisible = false

        passwordField.setOnTouchListener { _, event ->

            if (event.action == MotionEvent.ACTION_UP) {

                val drawable = passwordField.compoundDrawables[2]

                if (drawable != null &&
                    event.x >= passwordField.width - passwordField.paddingEnd - drawable.bounds.width()
                ) {

                    isVisible = !isVisible

                    val method =
                        if (isVisible)
                            android.text.method.HideReturnsTransformationMethod.getInstance()
                        else
                            android.text.method.PasswordTransformationMethod.getInstance()

                    passwordField.transformationMethod = method
                    confirmPasswordField.transformationMethod = method

                    passwordField.setSelection(passwordField.text.length)
                    confirmPasswordField.setSelection(confirmPasswordField.text.length)

                    return@setOnTouchListener true
                }
            }

            false
        }
    }
private val registeredFullName = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_registration_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val registrationBtn = findViewById<Button>(R.id.btnRegister)
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


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

            registrationBtn.setBackgroundColor(Color.parseColor("#7B1FA2"))
            registrationBtn.setTextColor(Color.WHITE)
            mainLayout.setBackgroundColor(Color.parseColor("#0D1B2A")) // dark mode
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

        } else {
            registrationBtn.setBackgroundColor(Color.parseColor("#FFD600")) // Yellow
            registrationBtn.setTextColor(Color.BLACK)
            mainLayout.setBackgroundResource(R.drawable.registration_bg)
//textview
            txtFullName.setTextColor(Color.BLACK)
            txtEmail.setTextColor(Color.BLACK)
            txtPassword.setTextColor(Color.BLACK)
            txtConfirmPassword.setTextColor(Color.BLACK)

            fullName.setTextColor(Color.BLACK)
            email.setTextColor(Color.BLACK)
            password.setTextColor(Color.BLACK)
            confirmPassword.setTextColor(Color.BLACK)

            fullName.setHintTextColor(Color.GRAY)
            email.setHintTextColor(Color.GRAY)
            password.setHintTextColor(Color.GRAY)
            confirmPassword.setHintTextColor(Color.GRAY)
            fullName.setBackgroundResource(R.drawable.input_custom)
            email.setBackgroundResource(R.drawable.input_custom)
            password.setBackgroundResource(R.drawable.input_custom)
            confirmPassword.setBackgroundResource(R.drawable.input_custom)

        }
        setupPasswordToggle(password, confirmPassword)
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            /* for animation only hehe   // fade out (smooth disappearance)
            mainLayout.animate()
                .alpha(0f)
                .setDuration(150)
                .withEndAction {

                    // switch theme AFTER fade out
                    if (isChecked) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }

                    // fade in (smooth appearance)
                    mainLayout.post {
                        mainLayout.alpha = 0f
                        mainLayout.animate()
                            .alpha(1f)
                            .setDuration(200)
                            .start()
                    }
                }
                .start()

        }*/
        }
        registrationBtn.setOnClickListener {
            val inputname = fullName.text.toString().trim()
            val inputemail = email.text.toString().trim()
            val inputpass = password.text.toString()
            val inputconfirmpass = confirmPassword.text.toString()
            val nameExits = registeredFullName.any{ it.equals(inputname,ignoreCase = true) }
            if (inputname.isBlank()) {
                Toast.makeText(this, "Full Name is Required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (nameExits){
                Toast.makeText(this, "Name is already taken please input another one", Toast.LENGTH_SHORT).show()
             return@setOnClickListener
            }
            if (inputemail.isBlank() ||
                !android.util.Patterns.EMAIL_ADDRESS.matcher(inputemail).matches() ||
                !inputemail.endsWith("@gmail.com", ignoreCase = true)) {

                Toast.makeText(this, "Valid @gmail.com address is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (inputpass.length < 8){
                Toast.makeText(this, "Password must be at least 8 Characters Long", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
            }
            if (!inputconfirmpass.equals(inputpass)){
                Toast.makeText(this, "Your Password is not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            registeredFullName.add(inputname)
            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
            fullName.text.clear()
            email.text.clear()
            password.text.clear()
            confirmPassword.text.clear()
        }

    }
}
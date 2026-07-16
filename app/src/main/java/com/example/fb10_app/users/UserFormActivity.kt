package com.example.fb10_app.users

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fb10_app.data.User
import com.example.fb10_app.data.UserRepository
import com.example.fb10_app.data.UserRequest
import com.example.fb10_app.databinding.ActivityUserFormBinding
import kotlinx.coroutines.launch

class UserFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserFormBinding
    private val repository = UserRepository()
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getIntExtra(EXTRA_USER_ID, -1).takeIf { it > 0 }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (userId == null) "Add User" else "Edit User"

        userId?.let { loadUser(it) }
        binding.buttonSave.setOnClickListener { saveUser() }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadUser(id: Int) {
        lifecycleScope.launch {
            runCatching { repository.getUser(id) }
                .onSuccess { showUser(it) }
                .onFailure {
                    Toast.makeText(this@UserFormActivity, it.message, Toast.LENGTH_LONG).show()
                    finish()
                }
        }
    }

    private fun showUser(user: User) = with(binding) {
        inputUsername.setText(user.username)
        inputFirstName.setText(user.firstName)
        inputMiddleName.setText(user.middleName.orEmpty())
        inputLastName.setText(user.lastName)
        inputEmail.setText(user.email)
        inputPassword.hint = "Leave blank to keep old password"
    }

    private fun saveUser() {
        val username = binding.inputUsername.text.toString().trim()
        val firstName = binding.inputFirstName.text.toString().trim()
        val middleName = binding.inputMiddleName.text.toString().trim()
        val lastName = binding.inputLastName.text.toString().trim()
        val email = binding.inputEmail.text.toString().trim()
        val password = binding.inputPassword.text.toString()

        if (username.isBlank() || firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
            Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_LONG).show()
            return
        }

        if (userId == null && password.isBlank()) {
            Toast.makeText(this, "Password is required for new users", Toast.LENGTH_LONG).show()
            return
        }

        val request = UserRequest(
            username = username,
            firstName = firstName,
            middleName = middleName.ifBlank { null },
            lastName = lastName,
            email = email,
            password = password.ifBlank { null },
            photo = null
        )

        lifecycleScope.launch {
            runCatching {
                val id = userId
                if (id == null) {
                    repository.createUser(request)
                } else {
                    repository.updateUser(id, request)
                }
            }.onSuccess {
                Toast.makeText(this@UserFormActivity, "Saved", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            }.onFailure {
                Toast.makeText(this@UserFormActivity, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val EXTRA_USER_ID = "extra_user_id"
    }
}

package com.example.fb10_app

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_account, container, false)

        val layout = view.findViewById<ScrollView>(R.id.accountlayout)

        val txtDisplayName = view.findViewById<TextView>(R.id.txtDisplayName)
        val txtUsername = view.findViewById<TextView>(R.id.txtUsername)
        val txtFullName = view.findViewById<TextView>(R.id.txtFullName)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)

        val btnEditImage = view.findViewById<FloatingActionButton>(R.id.btnEditImage)
        val btnEditEmail = view.findViewById<ImageButton>(R.id.btnEditEmail)
        val btnEditUsername = view.findViewById<ImageButton>(R.id.btnEditUsername)

        val lblUsername = view.findViewById<TextView>(R.id.usn)
        val lblFullName = view.findViewById<TextView>(R.id.fulname)
        val lblEmail = view.findViewById<TextView>(R.id.emailadd)

        val btnChangePassword = view.findViewById<Button>(R.id.btnChangePassword)

        // THEME PREFS
        val themePrefs = requireActivity().getSharedPreferences(
            "ThemePrefs",
            Context.MODE_PRIVATE
        )

        // USER DATA PREFS (FIXED)
        val userPrefs = requireActivity().getSharedPreferences(
            "UserData",
            Context.MODE_PRIVATE
        )

        // LOAD SAVED DATA
        val fullName = userPrefs.getString("fullname", "No Name")
        val email = userPrefs.getString("email", "No Email")
        val username = userPrefs.getString("username", "Username")

        txtDisplayName.text = fullName
        txtFullName.text = fullName
        txtEmail.text = email
        txtUsername.text = username

        // DARK MODE
        if (themePrefs.getBoolean("dark_mode", false)) {

            val accent = Color.parseColor("#4ADE80")

            layout.setBackgroundColor(Color.parseColor("#0F172A"))

            txtDisplayName.setTextColor(accent)
            lblUsername.setTextColor(accent)
            lblFullName.setTextColor(accent)
            lblEmail.setTextColor(accent)

            txtUsername.setTextColor(Color.WHITE)
            txtFullName.setTextColor(Color.WHITE)
            txtEmail.setTextColor(Color.WHITE)

            btnChangePassword.setBackgroundColor(Color.parseColor("#7B1FA2"))
            btnChangePassword.setTextColor(Color.WHITE)

            btnEditImage.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#8B5CF6"))

            btnEditUsername.imageTintList =
                ColorStateList.valueOf(accent)

            btnEditEmail.imageTintList =
                ColorStateList.valueOf(accent)

        } else {

            layout.setBackgroundResource(R.drawable.registration_bg)

            val accent = Color.parseColor("#2E7D32")

            txtDisplayName.setTextColor(accent)
            lblUsername.setTextColor(accent)
            lblFullName.setTextColor(accent)
            lblEmail.setTextColor(accent)

            txtUsername.setTextColor(Color.BLACK)
            txtFullName.setTextColor(Color.BLACK)
            txtEmail.setTextColor(Color.BLACK)

            btnChangePassword.setBackgroundColor(accent)
            btnChangePassword.setTextColor(Color.WHITE)

            btnEditImage.backgroundTintList =
                ColorStateList.valueOf(accent)

            btnEditUsername.imageTintList =
                ColorStateList.valueOf(accent)

            btnEditEmail.imageTintList =
                ColorStateList.valueOf(accent)
        }

        // EDIT USERNAME
        btnEditUsername.setOnClickListener {

            val editText = android.widget.EditText(requireContext())
            editText.hint = "Edit Username"

            android.app.AlertDialog.Builder(requireContext())
                .setTitle("Username")
                .setView(editText)
                .setPositiveButton("Save") { _, _ ->

                    val newUsername = editText.text.toString()

                    userPrefs.edit()
                        .putString("username", newUsername)
                        .apply()

                    txtUsername.text = newUsername
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        // EDIT EMAIL
        btnEditEmail.setOnClickListener {

            val editText = android.widget.EditText(requireContext())
            editText.hint = "Edit Email"

            android.app.AlertDialog.Builder(requireContext())
                .setTitle("Email")
                .setView(editText)
                .setPositiveButton("Save") { _, _ ->

                    val newEmail = editText.text.toString()

                    userPrefs.edit()
                        .putString("email", newEmail)
                        .apply()

                    txtEmail.text = newEmail
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        return view
    }
}
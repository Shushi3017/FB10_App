package com.example.fb10_app.users

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.fb10_app.data.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UserDetailsDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val name = requireArguments().getString(ARG_NAME).orEmpty()
        val username = requireArguments().getString(ARG_USERNAME).orEmpty()
        val email = requireArguments().getString(ARG_EMAIL).orEmpty()

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(name)
            .setMessage("Username: $username\nEmail: $email")
            .setPositiveButton("OK", null)
            .create()
    }

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_USERNAME = "username"
        private const val ARG_EMAIL = "email"

        fun newInstance(user: User): UserDetailsDialogFragment {
            return UserDetailsDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, "${user.firstName} ${user.lastName}")
                    putString(ARG_USERNAME, user.username)
                    putString(ARG_EMAIL, user.email)
                }
            }
        }
    }
}

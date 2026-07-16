package com.example.fb10_app.users
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ConfirmDeleteDialogFragment : DialogFragment() {
    interface Listener {
        fun onDeleteConfirmed(userId: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val userId = requireArguments().getInt(ARG_ID)
        val username = requireArguments().getString(ARG_USERNAME).orEmpty()

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete user")
            .setMessage("Delete $username?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Delete") { _, _ ->
                (requireActivity() as Listener).onDeleteConfirmed(userId)
            }
            .create()
    }

    companion object {
        private const val ARG_ID = "id"
        private const val ARG_USERNAME = "username"

        fun newInstance(userId: Int, username: String): ConfirmDeleteDialogFragment {
            return ConfirmDeleteDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, userId)
                    putString(ARG_USERNAME, username)
                }
            }
        }
    }
}

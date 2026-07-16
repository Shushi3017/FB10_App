package com.example.fb10_app.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fb10_app.databinding.ActivityUserListBinding
import kotlinx.coroutines.launch

class UserListActivity : AppCompatActivity(), ConfirmDeleteDialogFragment.Listener {
    private lateinit var binding: ActivityUserListBinding
    private val viewModel: UserViewModel by viewModels()

    private val formLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) viewModel.loadUsers()
        }

    private val adapter = UserAdapter(
        onView = { user ->
            UserDetailsDialogFragment.newInstance(user)
                .show(supportFragmentManager, "UserDetailsDialog")
        },
        onEdit = { user -> openForm(user.id) },
        onDelete = { user ->
            ConfirmDeleteDialogFragment.newInstance(user.id, user.username)
                .show(supportFragmentManager, "ConfirmDeleteDialog")
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerUsers.adapter = adapter
        binding.fabAdd.setOnClickListener { openForm() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressBar.visibility =
                        if (state.loading) View.VISIBLE else View.GONE
                    binding.textEmpty.visibility =
                        if (!state.loading && state.users.isEmpty()) View.VISIBLE else View.GONE
                    adapter.submitList(state.users)
                    state.error?.let {
                        Toast.makeText(this@UserListActivity, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewModel.loadUsers()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDeleteConfirmed(userId: Int) {
        viewModel.deleteUser(userId)
    }

    private fun openForm(userId: Int? = null) {
        val intent = Intent(this, UserFormActivity::class.java)
        if (userId != null) intent.putExtra(UserFormActivity.EXTRA_USER_ID, userId)
        formLauncher.launch(intent)
    }
}

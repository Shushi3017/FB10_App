package com.example.fb10_app.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fb10_app.data.User
import com.example.fb10_app.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
data class UsersUiState(
    val loading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)
class UserViewModel : ViewModel() {
    private val repository = UserRepository()
    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState: StateFlow<UsersUiState> = _uiState.asStateFlow()
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            runCatching { repository.getUsers() }
                .onSuccess { users ->
                    _uiState.value = UsersUiState(users = users)
                }
                .onFailure { throwable ->
                    _uiState.value = UsersUiState(error = throwable.message)
                }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            runCatching { repository.deleteUser(id) }
                .onSuccess { loadUsers() }
                .onFailure { throwable ->
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        error = throwable.message
                    )
                }
        }
    }
}
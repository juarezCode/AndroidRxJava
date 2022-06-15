package com.juarez.androidrxjava.users.presentation

import androidx.lifecycle.ViewModel
import com.juarez.androidrxjava.users.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private var _userState = MutableStateFlow<UserState>(UserState.Empty)
    val userState = _userState.asStateFlow()

    fun getUsers() {
        _userState.value = UserState.Loading(true)
        repository.getUsers(
            { users ->
                _userState.value = UserState.Success(users)
            }, { t ->
                _userState.value = UserState.Error(t)
            }, {
                _userState.value = UserState.Loading(false)
            })
    }

    override fun onCleared() {
        super.onCleared()
        repository.onDestroy()
    }
}
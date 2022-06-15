package com.juarez.androidrxjava

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {
    private val repository = UserRepository()
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
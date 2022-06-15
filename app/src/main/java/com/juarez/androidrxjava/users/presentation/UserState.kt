package com.juarez.androidrxjava.users.presentation

import com.juarez.androidrxjava.users.domain.User

sealed class UserState {
    object Empty : UserState()
    data class Loading(val isLoading: Boolean) : UserState()
    data class Error(val exception: Throwable) : UserState()
    data class Success(val users: List<User>) : UserState()
}

package com.juarez.androidrxjava

sealed class UserState {
    object Empty : UserState()
    data class Loading(val isLoading: Boolean) : UserState()
    data class Error(val exception: Throwable) : UserState()
    data class Success(val users: List<User>) : UserState()
}

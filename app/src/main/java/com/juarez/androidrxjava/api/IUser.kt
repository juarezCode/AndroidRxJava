package com.juarez.androidrxjava.api

import com.juarez.androidrxjava.User
import io.reactivex.disposables.CompositeDisposable

interface IUser {
    interface View {
        fun showLoader(show: Boolean)
        fun onGetUserSuccess(users: List<User>)
        fun onGetUserError(t: Throwable)
    }

    interface Presenter {
        fun getUsers()
        fun onGetUserSuccess(users: List<User>)
        fun onGetUserError(t: Throwable)
        fun onDestroy()
    }

    interface Repository {
        val compositeDisposable: CompositeDisposable
        fun getUsers()
        fun onDestroy() = compositeDisposable.clear()
    }
}
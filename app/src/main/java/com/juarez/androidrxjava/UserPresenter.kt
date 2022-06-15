package com.juarez.androidrxjava

import com.juarez.androidrxjava.api.IUser

class UserPresenter(
    private val view: IUser.View
) : IUser.Presenter {
    private val repository = UserRepository(this)

    override fun getUsers() {
        view.showLoader(true)
        repository.getUsers()
    }

    override fun onGetUserSuccess(users: List<User>) {
        view.showLoader(false)
        view.onGetUserSuccess(users)
    }

    override fun onGetUserError(t: Throwable) {
        view.showLoader(false)
        view.onGetUserError(t)
    }

    override fun onDestroy() {
        repository.onDestroy()
    }
}
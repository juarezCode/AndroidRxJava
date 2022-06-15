package com.juarez.androidrxjava

import com.juarez.androidrxjava.api.IUser
import com.juarez.androidrxjava.api.WebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserRepository(private val presenter: IUser.Presenter) : IUser.Repository {
    override val compositeDisposable = CompositeDisposable()

    override fun getUsers() {
        val requestObservable = WebService.createRetrofit().getUsers()
        compositeDisposable.add(
            requestObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users -> presenter.onGetUserSuccess(users) },
                    { t -> presenter.onGetUserError(t) }
                )
        )
    }
}
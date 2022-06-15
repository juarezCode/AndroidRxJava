package com.juarez.androidrxjava

import android.util.Log
import com.juarez.androidrxjava.api.WebService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UserRepository(
    private val defaultScheduler: Scheduler = Schedulers.io()
) {
    private val compositeDisposable = CompositeDisposable()

    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit
    ) {
        val requestObservable = WebService.createRetrofit().getUsers()
        compositeDisposable.add(
            requestObservable
                .subscribeOn(defaultScheduler)
                .delay(2, TimeUnit.SECONDS)
                .doOnNext { data -> Log.d("RX", "1 $data") }
                .map { users -> users.map { user -> user.copy(name = "${user.name}${user.id}") } }
                .doOnNext { data -> Log.d("RX", "2 $data") }
                .filter { users -> users.size > 5 }
                .doOnNext { data -> Log.d("RX", "3 $data") }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(onComplete)
                .subscribe(
                    { users -> onSuccess(users) },
                    { t -> onError(t) },
                )
        )
    }

    fun onDestroy() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}
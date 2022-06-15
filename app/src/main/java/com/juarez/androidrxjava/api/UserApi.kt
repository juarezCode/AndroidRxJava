package com.juarez.androidrxjava.api

import com.juarez.androidrxjava.users.domain.User
import io.reactivex.Flowable
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun getUsers(): Flowable<List<User>>
}
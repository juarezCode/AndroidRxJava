package com.juarez.androidrxjava.api

import com.juarez.androidrxjava.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun getUsers(): Observable<List<User>>
}
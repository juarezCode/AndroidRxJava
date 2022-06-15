package com.juarez.androidrxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.juarez.androidrxjava.api.IUser

class MainActivity : AppCompatActivity(), IUser.View {
    private val presenter = UserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUsers()
    }

    private fun getUsers() {
        presenter.getUsers()
    }

    override fun onGetUserSuccess(users: List<User>) {
        Log.d("RX", "success $users")
    }

    override fun onGetUserError(t: Throwable) {
        Log.d("RX", "error ${t.localizedMessage}")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
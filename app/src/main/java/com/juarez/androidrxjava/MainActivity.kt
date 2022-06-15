package com.juarez.androidrxjava

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.juarez.androidrxjava.api.IUser
import com.juarez.androidrxjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IUser.View {
    private lateinit var binding: ActivityMainBinding
    private val presenter = UserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBarRx.isVisible = false
        getUsers()
    }

    private fun getUsers() {
        presenter.getUsers()
    }

    override fun showLoader(show: Boolean) {
        binding.progressBarRx.isVisible = show
    }

    override fun onGetUserSuccess(users: List<User>) {
        Log.d("RX", "success $users")
        binding.txtResult.text = "success $users"
    }

    override fun onGetUserError(t: Throwable) {
        Log.d("RX", "error ${t.localizedMessage}")
        binding.txtResult.text = "error ${t.localizedMessage}"
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
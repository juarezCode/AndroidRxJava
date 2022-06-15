package com.juarez.androidrxjava

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.juarez.androidrxjava.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressBarRx.isVisible = false

        getUsers()

        collectUserState()
    }

    private fun getUsers() {
        viewModel.getUsers()
    }

    private fun collectUserState() {
        lifecycleScope.launchWhenStarted {
            viewModel.userState.collect { userState ->
                when (userState) {
                    is UserState.Loading -> {
                        binding.progressBarRx.isVisible = userState.isLoading
                    }
                    is UserState.Error -> {
                        Log.d("RX", "error ${userState.exception.localizedMessage}")
                        binding.txtResult.text = "error ${userState.exception.localizedMessage}"
                    }
                    is UserState.Success -> {
                        Log.d("RX", "success $userState.users")
                        binding.txtResult.text = "success $userState.users"
                    }
                    else -> Unit
                }
            }
        }
    }
}
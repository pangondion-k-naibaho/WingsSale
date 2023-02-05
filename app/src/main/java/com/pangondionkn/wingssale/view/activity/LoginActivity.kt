package com.pangondionkn.wingssale.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.pangondionkn.wingssale.R
import com.pangondionkn.wingssale.databinding.ActivityLoginBinding
import com.pangondionkn.wingssale.model.data_class.Login
import com.pangondionkn.wingssale.model.data_dummy.Dummy_Data.getDummyUser
import com.pangondionkn.wingssale.viewmodel.LoginViewModel
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.simpleName
    private var _binding: ActivityLoginBinding?= null
    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        addDummyUser()

        setUpLogin()
    }

    private fun addDummyUser(){
        Log.d(TAG, "Add new User....")
        loginViewModel.addUserLogin(getDummyUser())
    }

    private fun setUpLogin(){

        binding.btnLogin.setOnClickListener {
            val deliveredUsername = binding.etUsername.text
            val deliveredPassword = binding.etPassword.text
            Log.d(TAG, "username : $deliveredUsername, password: $deliveredPassword")
            val result = loginViewModel.getResultCheckUserLogin(deliveredUsername.toString(), deliveredPassword.toString())

            Log.d(TAG, "result : $result")
            when(result != 0){
                true -> {
                    Log.d(TAG, "Login Sukses")
                    startActivity(
                        ListProductActivity.newIntent(this, deliveredUsername.toString())
                    )
                }
                else -> {
                    Log.d(TAG, "Login Gagal")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        loginViewModel.removeAllUserLogin()
    }
}
package com.capstone.compassionly.presentation.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import com.capstone.compassionly.utility.Resources
import androidx.lifecycle.lifecycleScope
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityLoginBinding
import com.capstone.compassionly.presentation.feature.login.viewmodel.LoginViewModel
import com.capstone.compassionly.presentation.feature.onboarding.OnBoardingActivity
import com.capstone.compassionly.presentation.feature.onboarding.viewmodel.OnBoardViewModel
import com.capstone.compassionly.repository.di.StateInjection
import com.capstone.compassionly.utility.Utils
import com.capstone.compassionly.utility.viewmodelfactory.ViewModelFactory
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private val factory: ViewModelFactory = ViewModelFactory.getInstance()
    private val viewModel: LoginViewModel by viewModels {
        factory
    }
    private val onBoardViewModel : OnBoardViewModel by viewModels {
        StateInjection.onBoardInjection(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //stateCheck()
        Utils.changeStatusBarColorWhite(this)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = Firebase.auth

        viewModel.getSendTokenResult.observe(this) { resource ->
            when (resource) {
                is Resources.OnFailure -> {
                    val errorMessage = resource.message
                    errorMessage?.let {
                       Log.d("LOGINTEST","$errorMessage")
                    }
                }
                is Resources.Success -> {
                    val userModel = resource.data
                    userModel?.let {
                        Log.d("LOGINTEST","$userModel")
                    }
                }

                is Resources.Loading -> {
                    Log.d("LOGINTEST","loading")
                }
            }
        }

        binding.signInButton.setOnClickListener {
            signIn()
        }

        viewModel.loginResult.observe(this) { user ->
            updateUI(user)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun signIn() {
        val credentialManager = CredentialManager.create(this)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(getString(R.string.web_client_id))
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result: GetCredentialResponse = credentialManager.getCredential(
                    request = request,
                    context = this@LoginActivity,
                )
                viewModel.handleSignIn(result)
            } catch (e: GetCredentialException) {
                Log.d("Error", e.message.toString())
            }
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            viewModel.sendTokenToServer()
            startActivity(Intent(this@LoginActivity, OnBoardingActivity::class.java))
            finish()
        }
    }

    private fun stateCheck() {
        onBoardViewModel.getOnBoardState().observe(this) {
            if (it.isNullOrBlank()) {
                val intent = Intent(this@LoginActivity, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}

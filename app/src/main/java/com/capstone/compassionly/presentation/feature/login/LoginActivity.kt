package com.capstone.compassionly.presentation.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityLoginBinding
import com.capstone.compassionly.models.DataLogin
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.LoginResponse
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.local.LocalUser
import com.capstone.compassionly.presentation.feature.dashboard.DashboardActivity
import com.capstone.compassionly.presentation.feature.login.viewmodel.LoginViewModel
import com.capstone.compassionly.presentation.feature.users_data.FormCompleteUserProfile
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    private val viewModel: LoginViewModel by viewModels {
        CommonInjector.common(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Utils.changeStatusBarColorWhite(this)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        auth = Firebase.auth

        binding.signInButton.setOnClickListener {
            signIn()
        }

        viewModel.token.observe(this) { token ->
            token?.let {
                viewModel.sendToken(it).observe(this) { resources ->
                    if (resources != null) {
                        when (resources) {
                            is Resources.Loading -> {
                                Log.d("LoginActivity", "Loading...")
                            }

                            is Resources.Success -> {
                                Log.d("LoginActivity", "$resources")
                                viewModel.loginResult.observe(this) { user ->
                                    val result = resources.data as LoginResponse
                                    if (resources.data.javaClass.isAssignableFrom(LoginResponse::class.java)) {
                                        checkState(result.data, token = result.data?.accessToken)
                                    } else {
                                        updateUI(false, result.data?.accessToken)
                                    }
                                }
                            }

                            is Resources.Error -> {
                                Toast.makeText(
                                    application,
                                    "Error: ${resources.error}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkState(user: DataLogin?, token: String? = null) {
        if (token.isNullOrEmpty()) {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.token_not_found))
                setMessage(R.string.ask_login)
                setPositiveButton(R.string.signIn) { _, _ ->
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }        } else {
            if (
                user?.user?.firstName == null ||
                user.user.lastName == null ||
                user.user.phoneNumber == null ||
                user.user.schoolId == null ||
                user.user.schoolMajorId == null
            ) {
                updateUI(true, token)
            } else {
                updateUI(false, token)
            }
        }
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

    private fun updateUI(needUpdateData: Boolean, token: String?) {
        if (needUpdateData) {
            val intent = Intent(this@LoginActivity, FormCompleteUserProfile::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
            finishAffinity()
        } else {
            storeUserToLocal(token!!)
            startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            finishAffinity()
        }
    }

    private fun storeUserToLocal(token: String) = runBlocking {
        viewModel.getMe(token).observe(this@LoginActivity) { resources ->
            when (resources) {
                is Resources.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("LoginActivity", "Loading...")
                }

                is Resources.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val data = resources.data as SuccessResponse<*>
                    val detailUser = data.data as DetailUserModel
                    viewModel.store(LocalUser(0, detailUser))
                    viewModel.storeToken(token)
                }

                is Resources.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        application,
                        "Error: ${resources.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}

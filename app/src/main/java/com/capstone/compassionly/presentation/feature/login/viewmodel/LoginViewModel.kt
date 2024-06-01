package com.capstone.compassionly.presentation.feature.login.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.models.ErrorModel2
import com.capstone.compassionly.models.UserModel
import com.capstone.compassionly.repository.core.network.UserRepository
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import com.capstone.compassionly.utility.Resources
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response


class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<FirebaseUser?>()
    val loginResult: LiveData<FirebaseUser?> = _loginResult

    fun handleSignIn(result: GetCredentialResponse) {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential =
                            GoogleIdTokenCredential.createFrom(credential.data)
                        firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                    }
                } else {
                    Log.e(TAG, "Unexpected type of credential")
                }
            }

            else -> {
                Log.e(TAG, "Unexpected type of credential")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginResult.value = FirebaseAuth.getInstance().currentUser
                    sendTokenToServer()
                    Log.d(TAG, "$idToken")
                } else {
                    _loginResult.value = null
                }
            }
    }

    private val sendTokenResult: MutableLiveData<Resources<UserModel>> = MutableLiveData()
    val getSendTokenResult: LiveData<Resources<UserModel>> = sendTokenResult
    private fun sendToken(token: String) = viewModelScope.launch {
        sendTokenResult.postValue(Resources.Loading())
        try {
            val response = userRepository.sendToken(token)
            sendTokenResult.postValue(handlerSendToken(response))
        } catch (e: Exception) {
            sendTokenResult.postValue(Resources.OnFailure(e.message ?: "Unknown error"))
        }
    }

    private fun sendTokenToServer() {
        val mUser = FirebaseAuth.getInstance().currentUser
        mUser?.getIdToken(true)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val idToken: String? = task.result.token
                    // Send token to your backend via HTTPS
                    if (idToken != null) {
                        sendToken(idToken)
                        Log.d(TAG, "token id: $idToken")
                    }
                } else {
                    Log.e(TAG, "failed get da token")
                }
            }
    }

    private fun handlerSendToken(response: Response<UserModel>): Resources<UserModel> {
        return try {
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                Resources.Success(body)
            } else {
                val jsonInString = response.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorModel2::class.java)
                Resources.OnFailure(errorBody?.message ?: "Unknown error")
            }
        } catch (e: Exception) {
            val errorMessage = if (e is HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorModel2::class.java)
                errorBody?.message ?: "HTTP error"
            } else {
                e.message ?: "Unknown error"
            }
            Resources.OnFailure(errorMessage)
        }
    }

}




package com.capstone.compassionly.presentation.feature.login.viewmodel

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.capstone.compassionly.datasource.preference.datasupport.StateAppPreference
import com.capstone.compassionly.models.ErrorModel
import com.capstone.compassionly.models.local.LocalUser
import com.capstone.compassionly.repository.core.local.LocalDataSource
import com.capstone.compassionly.repository.core.network.UserRepository
import com.capstone.compassionly.utility.Resources
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException


class LoginViewModel(
    private val userRepository: UserRepository,
    private val localDataSource: LocalDataSource,
    private val stateAppPreference: StateAppPreference,

) : ViewModel() {

    // API

    private val _loginResult = MutableLiveData<FirebaseUser?>()
    val loginResult: LiveData<FirebaseUser?> = _loginResult

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

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
                    Log.d(TAG, "FirebaseAuth successful: ${task.result?.user}")
                    sendTokenToServer()
                    Log.d(TAG, "Token: $idToken")
                } else {
                    _loginResult.value = null
                }
            }
    }

    fun sendToken(token: String) = userRepository.sendToken(token)

    private fun sendTokenToServer() {
        val mUser = FirebaseAuth.getInstance().currentUser
        mUser?.getIdToken(true)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val idToken: String? = task.result.token
                    // Send token to your backend via HTTPS
                    if (idToken != null) {
                        _token.value = idToken
                        Log.d(TAG, "token id: $idToken")
                    }
                } else {
                    Log.e(TAG, "failed get da token")
                }
            }
    }

    fun getMe(token: String) = liveData {
        emit(Resources.Loading)
        try {
            val response = userRepository.getMe(token)
            if (response.isSuccessful) {
                response.body().apply {
                    emit(Resources.Success(this))
                }
            }
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorModel::class.java)
            emit(Resources.Error(errorBody))
        }

    }

    // LOCAL
    fun store (localUser: LocalUser?) = viewModelScope.launch {
        localDataSource.insertUser(localUser)
    }
    fun storeToken(token: String) = viewModelScope.launch{
        stateAppPreference.setAccessToken(token)
    }

    companion object {
        const val TAG = "LOGIN VIEW MODEL TEST"
    }

}




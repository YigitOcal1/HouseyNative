package com.example.houseynative.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseynative.model.Response.*
import com.example.houseynative.model.UserModel
import com.example.houseynative.repository.authrepository.AuthRepository
import com.example.houseynative.repository.authrepository.LoginWithFirebase
import com.example.houseynative.repository.authrepository.LogoutWithFirebaseResponse
import com.example.houseynative.repository.authrepository.RegisterWithFirebaseResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val currentUser get() = repository.currentUser

    var loginUser by mutableStateOf<LoginWithFirebase>(Success(false))
        private set

    var registerUser by mutableStateOf<RegisterWithFirebaseResponse>(Success(false))
        private set

    var logoutUser by mutableStateOf<LogoutWithFirebaseResponse>(Success(false))
        private set

        fun logIn(
        email: String,
        password: String,
        home: () -> Unit
        ) = viewModelScope.launch {
        repository.loginWithFirebase(email, password, home).collect { response ->
            loginUser = response
        }
    }
    fun register(
        email: String,
        password: String,
        home: () -> Unit
    ) = viewModelScope.launch {
        repository.registerWithFirebase(email, password, home).collect { response ->
            registerUser = response
        }
    }
    fun logOut( home: () -> Unit)=viewModelScope.launch {
        repository.logOut(home).collect{
            response-> logoutUser=response
        }
    }

}



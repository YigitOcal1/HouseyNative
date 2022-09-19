package com.example.houseynative.viewmodel

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseynative.model.Response
import com.example.houseynative.model.UserModel
import com.example.houseynative.repository.AuthRepository
import com.example.houseynative.repository.LoginWithFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginScreenViewModel @Inject constructor(
    private val repository: AuthRepository
):ViewModel() {

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    private val _loginFlow = MutableStateFlow<LoginWithFirebase>(Response.Success(false))
    val loginFlow: StateFlow<LoginWithFirebase> = _loginFlow

    private val _registerFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Response<FirebaseUser>?> = _registerFlow

    init {
        if(repository.currentUser != null){
            _loginFlow.value = Response.Success(repository.currentUser!!)
        }
    }

    fun logIn(email: String,
              password: String,
              home: () -> Unit) =viewModelScope.launch{
        _loginFlow.value=Response.Loading
        val result=repository.loginWithFirebase(email,password, home)
        _loginFlow.value=result
    }
}
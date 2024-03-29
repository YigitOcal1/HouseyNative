package com.example.houseynative.repository.authrepository

import com.example.houseynative.model.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow


typealias LoginWithFirebase=Response<Boolean>
typealias RegisterWithFirebaseResponse=Response<Boolean>
typealias LogoutWithFirebaseResponse=Response<Boolean>

interface AuthRepository {

    val currentUser: Boolean

    fun loginWithFirebase(email: String,password:String,home:()->Unit): Flow<LoginWithFirebase>

    fun registerWithFirebase(email: String,password:String,home:()->Unit): Flow<RegisterWithFirebaseResponse>

    fun createUser(displayName:String?)

    fun logOut(home: () -> Unit): Flow<LogoutWithFirebaseResponse>
}
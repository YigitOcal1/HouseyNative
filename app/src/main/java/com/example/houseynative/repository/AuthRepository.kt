package com.example.houseynative.repository

import com.example.houseynative.model.Response
import kotlinx.coroutines.flow.Flow


typealias LoginWithFirebase=Response<Boolean>
typealias RegisterWithFirebaseResponse=Response<Boolean>

interface AuthRepository {

    fun loginWithFirebase(): Flow<LoginWithFirebase>

    fun registerWithFirebase(): Flow<RegisterWithFirebaseResponse>

}
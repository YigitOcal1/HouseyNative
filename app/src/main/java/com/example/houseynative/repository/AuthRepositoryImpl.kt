package com.example.houseynative.repository

import com.example.houseynative.model.Response.*
import com.example.houseynative.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override val currentUser: FirebaseUser?
    get() = auth.currentUser


    override fun loginWithFirebase(
        email: String,
        password: String,
        home: () -> Unit
    ) = flow {
        emit(Loading)
        try {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        home()
                    }
                }
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override fun registerWithFirebase(
        email: String,
        password: String,
        home: () -> Unit
    ) = flow {
        emit(Loading)
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    }
                }
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = UserModel(
            id = null,
            userId = userId.toString(),
            displayName = displayName.toString()
        ).toMap()
        FirebaseFirestore.getInstance().collection("users").add(user)
    }

    override fun logOut(home: () -> Unit)= flow {
        emit(Loading)
        try {
            auth.signOut()
            home()

            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

}

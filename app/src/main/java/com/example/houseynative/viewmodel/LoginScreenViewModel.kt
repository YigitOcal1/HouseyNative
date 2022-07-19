package com.example.houseynative.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseynative.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel:ViewModel() {

    private val  auth: FirebaseAuth= Firebase.auth

    fun createUserWithEmailAndPassword(email:String,password:String,home:()->Unit){
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task->
                    if (task.isSuccessful){
                        val displayName=task.result?.user?.email?.split("@")?.get(0)
                        createUser(displayName)
                        home()
                    }
                }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
private fun  createUser(displayName:String?){
    val userId= auth.currentUser?.uid
    val user=UserModel(id = null,userId=userId.toString(),displayName=displayName.toString()).toMap()
    FirebaseFirestore.getInstance().collection("users").add(user)
}

    fun logIn(email: String,password: String,home: () -> Unit)=viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task->
                    if (task.isSuccessful){
                        home()
                    }
                    else{
                        print("Giriş işlemi başarısız")
                    }

                }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}
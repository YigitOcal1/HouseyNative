package com.example.houseynative.model

data class UserModel(val id:String?,val userId:String,val displayName:String){



    fun toMap(): MutableMap<String,Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName

        )
    }
}

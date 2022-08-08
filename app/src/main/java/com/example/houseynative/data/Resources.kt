package com.example.houseynative.data



sealed class Resources<T>(val data: T? = null, val message: String? = null) {


    class Loading<T>(data:T?=null) : Resources<T>(data)
    class Success<T>(data: T) : Resources<T>(data)
    class Error<T>(message: String?,data: T?=null) : Resources<T>(data, message)

}
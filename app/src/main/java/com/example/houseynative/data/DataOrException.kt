package com.example.houseynative.data

data class ResourcesOrException<T,E:Exception> (

    var data: T?=null,
    var e:E?=null
)
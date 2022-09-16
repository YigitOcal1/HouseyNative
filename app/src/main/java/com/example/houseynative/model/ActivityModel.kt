package com.example.houseynative.model

data class ActivityModel(
    val id: String?,
    val title: String,
    val date: String,
    val location: String,
    val maxPeople: String,
    val ownerName: String


) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "title" to this.title,
            "date" to this.date,
            "location" to this.location,
            "maxPeople" to this.maxPeople,
            "ownerName" to this.ownerName

        )
    }
}


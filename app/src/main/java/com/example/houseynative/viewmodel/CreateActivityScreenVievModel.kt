package com.example.houseynative.viewmodel

import androidx.lifecycle.ViewModel
import com.example.houseynative.repository.ActivityRepository

class CreateActivityScreenVievModel(
    private val repository: ActivityRepository = ActivityRepository(),
) : ViewModel() {


    val user = repository.user()

    val hasUser: Boolean get() = repository.hasUser()

    private val userId: String get() = repository.getUserId()


    fun loadActivities() {
        if (hasUser) {
            if (userId.isNotBlank()) {
                repository.getUserActivities(userId)
            }
        } else {
            Throwable(message = "Kullanıcı bulunamadı")
        }
    }
}
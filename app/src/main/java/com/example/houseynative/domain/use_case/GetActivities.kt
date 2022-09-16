package com.example.houseynative.domain.use_case

import com.example.houseynative.repository.ActivityRepository

class GetActivities(
    private val repo: ActivityRepository
) {
    operator fun invoke()=repo.getActivitiesFromFirestore()
}
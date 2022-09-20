package com.example.houseynative.domain.use_case

import com.example.houseynative.repository.activityrepository.ActivityRepository

class GetActivities(
    private val repo: ActivityRepository
) {
    operator fun invoke()=repo.getActivitiesFromFirestore()
}
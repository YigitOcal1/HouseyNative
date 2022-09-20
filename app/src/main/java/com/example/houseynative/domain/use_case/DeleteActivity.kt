package com.example.houseynative.domain.use_case

import com.example.houseynative.repository.activityrepository.ActivityRepository

class DeleteActivity(
    private val repo: ActivityRepository
) {
    suspend operator fun invoke(activityId: String) = repo.deleteActivityFromFirestore(activityId)
}
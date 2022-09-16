package com.example.houseynative.domain.use_case

import com.example.houseynative.repository.ActivityRepository

class AddActivity(
    private val repo: ActivityRepository
) {

    suspend operator fun invoke(
        title: String,
        date: String,
        location: String,
        maxPeople: String,
        ownerName: String?
    ) = repo.addActivityToFirestore(title, date, location, maxPeople, ownerName)
}
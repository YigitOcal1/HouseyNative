package com.example.houseynative.repository.activityrepository

import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response
import kotlinx.coroutines.flow.Flow


typealias Activities = List<ActivityModel>
typealias ActivitiesResponse = Response<Activities>
typealias AddActivityResponse=Response<Boolean>
typealias DeleteActivityResponse=Response<Boolean>

interface ActivityRepository {
    fun getActivitiesFromFirestore(): Flow<ActivitiesResponse>

    fun addActivityToFirestore(
        title: String,
        date: String,
        location: String,
        maxPeople: String
    ): Flow<AddActivityResponse>

    fun deleteActivityFromFirestore(activityId: String): Flow<DeleteActivityResponse>


}
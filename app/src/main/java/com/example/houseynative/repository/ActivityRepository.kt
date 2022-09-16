package com.example.houseynative.repository

import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response
import kotlinx.coroutines.flow.Flow


typealias Activities = List<ActivityModel>
typealias ActivitiesResponse = Response<Activities>

interface ActivityRepository {
    fun getActivitiesFromFirestore(): Flow<ActivitiesResponse>

    fun addActivityToFirestore(
        title: String,
        date: String,
        location: String,
        maxPeople: String,
        ownerName: String?
    ): Flow<Response<Void?>>

    fun deleteActivityFromFirestore(activityId: String): Flow<Response<Void?>>
}
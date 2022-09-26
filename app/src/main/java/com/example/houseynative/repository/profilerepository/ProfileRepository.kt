package com.example.houseynative.repository.profilerepository

import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response
import kotlinx.coroutines.flow.Flow


typealias OwnerActivities = List<ActivityModel>
typealias OwnerActivitiesResponse=Response<OwnerActivities>
typealias JoinedActivities = List<ActivityModel>
typealias JoinedActivitiesResponse=Response<JoinedActivities>
typealias DeleteUserActivityResponse=Response<Boolean>
typealias LogoutWithFirebaseResponse= Response<Boolean>

interface ProfileRepository {

    val currentUser: Boolean

    fun getUserActivitiesFromFirestore(): Flow<OwnerActivitiesResponse>

    fun getJoinedActivitiesFromFirestore(): Flow<JoinedActivitiesResponse>

    fun deleteUserActivityFromFirestore(activityId: String): Flow<DeleteUserActivityResponse>

    fun logOut(home: () -> Unit): Flow<LogoutWithFirebaseResponse>


}
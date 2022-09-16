package com.example.houseynative.repository

import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response
import kotlinx.coroutines.flow.Flow

typealias SearchedActivities = List<ActivityModel>
typealias SearchedActivitiesResponse = Response<Activities>

interface SearchActivityRepository {

    fun getSearchedActivityFromFirestore(searchText: String): Flow<ActivitiesResponse>
}
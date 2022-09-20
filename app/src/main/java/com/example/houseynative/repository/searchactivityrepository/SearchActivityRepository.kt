package com.example.houseynative.repository.searchactivityrepository

import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response
import com.example.houseynative.repository.activityrepository.ActivitiesResponse
import kotlinx.coroutines.flow.Flow

typealias SearchedActivities = List<ActivityModel>
typealias SearchedActivitiesResponse = Response<SearchedActivities>

interface SearchActivityRepository {

    fun getSearchedActivityFromFirestore(searchText: String): Flow<ActivitiesResponse>
}
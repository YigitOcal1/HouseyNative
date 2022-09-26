package com.example.houseynative.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseynative.model.Response
import com.example.houseynative.repository.activityrepository.ActivitiesResponse
import com.example.houseynative.repository.activityrepository.DeleteActivityResponse
import com.example.houseynative.repository.profilerepository.LogoutWithFirebaseResponse
import com.example.houseynative.repository.profilerepository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val repo:ProfileRepository
): ViewModel(){

    var ownerActivitiesResponse by mutableStateOf<ActivitiesResponse>(Response.Loading)
        private set

    var ownerJoinedActivitiesResponse by mutableStateOf<ActivitiesResponse>(Response.Loading)
        private set

    var deleteUserActivityResponse by mutableStateOf<DeleteActivityResponse>(Response.Success(false))
        private set

    var logoutUser by mutableStateOf<LogoutWithFirebaseResponse>(Response.Success(false))
        private set

    var openDialog by mutableStateOf(false)
    private set

    init {
        getUserActivities()
        getUserJoinedActivities()
    }

    private fun getUserActivities() = viewModelScope.launch {
        repo.getUserActivitiesFromFirestore().collect { response->
            ownerActivitiesResponse=response
        }
    }

    private fun getUserJoinedActivities() = viewModelScope.launch {
        repo.getJoinedActivitiesFromFirestore().collect { response->
            ownerJoinedActivitiesResponse=response
        }
    }

    private fun deleteUserActivity(activityId:String)=viewModelScope.launch {
        repo.deleteUserActivityFromFirestore(activityId).collect(){ response->
            deleteUserActivityResponse=response
        }
    }

    fun logOut( home: () -> Unit)=viewModelScope.launch {
        repo.logOut(home).collect{
                response-> logoutUser=response
        }
    }


    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}

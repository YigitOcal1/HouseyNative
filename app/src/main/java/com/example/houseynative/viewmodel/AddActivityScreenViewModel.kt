package com.example.houseynative.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseynative.domain.use_case.UseCases
import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response
import com.example.houseynative.model.Response.*
import com.example.houseynative.repository.activityrepository.ActivitiesResponse
import com.example.houseynative.repository.activityrepository.AddActivityResponse
import com.example.houseynative.repository.activityrepository.DeleteActivityResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddActivityScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var activitiesResponse by mutableStateOf<ActivitiesResponse>(Loading)
        private set

    var addActivityResponse by mutableStateOf<AddActivityResponse>(Success(false))
        private set

    var deleteActivityResponse by mutableStateOf<DeleteActivityResponse>(Success(false))
        private set

    var openDialog by mutableStateOf(false)
        private set

    init {
        getActivities()
    }

    private fun getActivities() = viewModelScope.launch {
        useCases.getActivities().collect { response->
            activitiesResponse=response
        }
    }

    fun addActivity(
        title: String,
        date: String,
        location: String,
        maxPeople: String,
        ownerName: String?
    ) = viewModelScope.launch {
        useCases.addActivity(title, date, location, maxPeople, ownerName).collect { response ->
            addActivityResponse = response
        }
    }

    fun deleteActivity(activityId: String) = viewModelScope.launch {
        useCases.deleteActivity(activityId).collect { response ->
            deleteActivityResponse = response
        }
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}
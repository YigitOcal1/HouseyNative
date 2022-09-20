package com.example.houseynative.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.houseynative.model.Response.*
import com.example.houseynative.repository.searchactivityrepository.SearchActivityRepository
import com.example.houseynative.repository.searchactivityrepository.SearchedActivitiesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val repo: SearchActivityRepository
) :ViewModel(){
    var searchActivityResponse by mutableStateOf<SearchedActivitiesResponse>(Loading)

        private set

    fun getSearchActivity(searchText:String)=viewModelScope.launch {
        repo.getSearchedActivityFromFirestore(searchText).collect{
            response->searchActivityResponse=response
        }
    }
}




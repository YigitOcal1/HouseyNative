package com.example.houseynative.repository.searchactivityrepository

import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response.*
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SearchActivityRepositoryImpl @Inject constructor(
    private var activitiesQuery:Query
):SearchActivityRepository{
    override fun getSearchedActivityFromFirestore(searchText: String)= callbackFlow {
        if (searchText.isNotEmpty()){
            activitiesQuery=activitiesQuery.startAt(searchText).endAt("$searchText\uf8ff")
        }
        val snapshotListener = activitiesQuery.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val searchActivityList = snapshot.toObjects(ActivityModel::class.java)
                Success(searchActivityList)
            } else {
                Failure(e)
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
}
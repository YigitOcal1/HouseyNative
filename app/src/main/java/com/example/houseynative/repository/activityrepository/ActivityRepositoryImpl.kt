package com.example.houseynative.repository.activityrepository

import com.example.houseynative.data.ResourcesOrException
import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import com.example.houseynative.model.Response.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityRepositoryImpl @Inject constructor(
    private val activitiesRef: CollectionReference
) : ActivityRepository {

    fun user() = Firebase.auth.currentUser

    override fun getActivitiesFromFirestore() = callbackFlow {
        val snapshotListener = activitiesRef.orderBy("title").addSnapshotListener { snapshot, e ->
            val activitiesResponse = if (snapshot != null) {
                val activities = snapshot.toObjects(ActivityModel::class.java)
                Success(activities)
            } else {
                Failure(e)
            }
            trySend(activitiesResponse).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun addActivityToFirestore(
        title: String,
        date: String,
        location: String,
        maxPeople: String

    ) = flow {
        try {

            emit(Loading)
            val id = activitiesRef.document().id
            val activity = ActivityModel(
                id = id,
                title = title,
                date = date,
                location = location,
                maxPeople = maxPeople,
                ownerName = Firebase.auth.currentUser?.uid.toString()

            )
            activitiesRef.document(id).set(activity).await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

    override fun deleteActivityFromFirestore(activityId: String) = flow {
        try {
            emit(Loading)
            activitiesRef.document(activityId).delete().await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Failure(e))
        }
    }

}


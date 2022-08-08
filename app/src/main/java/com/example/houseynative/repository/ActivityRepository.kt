package com.example.houseynative.repository

import com.example.houseynative.data.ResourcesOrException
import com.example.houseynative.model.ActivityModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton


const val ACTIVITIES_COLLECTION_REF = "activities"

@Singleton
class ActivityRepository @Inject constructor(
    private var activitiesQuery: Query
) {

    fun user() = Firebase.auth.currentUser

    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    fun getUserId(): String = Firebase.auth.currentUser?.uid.orEmpty()

    private val activitiesRef: CollectionReference =
        Firebase.firestore.collection((ACTIVITIES_COLLECTION_REF))

    fun getActivityListFromFirestore(searchText: String) = callbackFlow {

        if (searchText.isNotEmpty()) {
            activitiesQuery = activitiesQuery.startAt(searchText).endAt("$searchText\uf8ff")
        }
        val snapshotListener = activitiesQuery.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val activitiesList = snapshot.toObjects(ActivityModel::class.java)

            } else {

            }

        }


    }



    fun getActivities(
        activityId: String,
        onError: (Throwable?) -> Unit,
        onSuccess: (ActivityModel) -> Unit
    ) {
        activitiesRef.document(activityId).get().addOnSuccessListener {

            onSuccess.invoke(it?.toObject(ActivityModel::class.java)!!)
        }
            //hatalı olabilir
            .addOnFailureListener { it ->
                onError.invoke(it.cause)
            }
    }

    fun addActivities(

        title: String,
        date: String,
        location: String,
        maxpeople: String,
        ownername: String,
        onComplete: (Boolean) -> Unit,
    ) {
        val documentId = activitiesRef.document().id
        val activity = ActivityModel(id = documentId, title, date, location, maxpeople, ownername)
        activitiesRef.document(documentId).set(activity).addOnCompleteListener { result ->
            onComplete.invoke(result.isSuccessful)
        }
    }

    fun deleteActivity(activityId: String, onComplete: (Boolean) -> Unit) {

        activitiesRef.document(activityId)
            .delete().addOnCompleteListener {
                onComplete.invoke(it.isSuccessful)
            }

    }

    fun updateActivity() {

    }

}
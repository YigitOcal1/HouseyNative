package com.example.houseynative.repository.profilerepository

import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val activitiesRef:CollectionReference
):ProfileRepository {


    override val currentUser=auth.currentUser!=null


    override fun getUserActivitiesFromFirestore() = callbackFlow {

        val snapshotListener = activitiesRef.whereIn("ownerName"==auth.currentUser?.uid.toString()).orderBy("title").addSnapshotListener { snapshot, e ->
            val activitiesResponse = if (snapshot != null) {
                val activities = snapshot.toObjects(ActivityModel::class.java)
                Response.Success(activities)
            } else {
                Response.Failure(e)
            }
            trySend(activitiesResponse).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }


    override fun getJoinedActivitiesFromFirestore(): Flow<JoinedActivitiesResponse> {
        TODO("Not yet implemented")
    }

    override fun deleteUserActivityFromFirestore(activityId: String) = flow {
        try {
            emit(Response.Loading)
            activitiesRef.document(activityId).delete().await()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }


    override fun logOut(home: () -> Unit)= flow {
        emit(Response.Loading)
        try {
            auth.signOut()
            home()

            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }
}
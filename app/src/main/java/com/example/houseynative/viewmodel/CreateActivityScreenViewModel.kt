package com.example.houseynative.viewmodel

import androidx.lifecycle.ViewModel
import com.example.houseynative.model.ActivityModel
import com.example.houseynative.model.UserModel
import com.example.houseynative.repository.ACTIVITIES_COLLECTION_REF
import com.example.houseynative.repository.ActivityRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

const val ACTIVITIES_COLLECTION_REF = "activities"


class CreateActivityScreenViewModel(
    private val repository: ActivityRepository = ActivityRepository(),
) : ViewModel() {



    private val auth: FirebaseAuth = Firebase.auth
    val user = repository.user()

    val hasUser: Boolean get() = repository.hasUser()

    private val userId: String get() = repository.getUserId()
    val userName = auth.currentUser?.email?.split("@")?.get(0)

     fun createActivity(
        title: String?,
        date: String?,
        location: String?,
        maxPeople: String?
        //ownerName: String?
    ) {
         val documentId=activitiesRef.document().id
        val userId = auth.currentUser?.uid
        val activity = ActivityModel(
            id = documentId,
            title = title.toString(),
            date = date.toString(),
            location = location.toString(),
            maxPeople = maxPeople.toString(),
            ownerName = userName.toString()
        )
         activitiesRef.document(documentId).set(activity)
        //FirebaseFirestore.getInstance().collection("activities").add(activity)
    }

    fun loadActivities() {
        if (hasUser) {
            if (userId.isNotBlank()) {
                repository.getUserActivities(userId)
            }
        } else {
            Throwable(message = "Kullanıcı bulunamadı")
        }
    }
    private val activitiesRef: CollectionReference =
        Firebase.firestore.collection((ACTIVITIES_COLLECTION_REF))


    fun getUserActivities(
        userId: String
    ): kotlinx.coroutines.flow.Flow<Resources<List<ActivityModel>>> = callbackFlow {

        var snapshotStateListener: ListenerRegistration? = null

        try {
            snapshotStateListener =
                activitiesRef.orderBy("date").whereEqualTo("ownername", userId)
                    .addSnapshotListener { snapshot, e ->
                        val response = if (snapshot != null) {
                            val activities = snapshot.toObjects(ActivityModel::class.java)
                            Resources.Success(data = activities)
                        } else {
                            Resources.Error(throwable = e?.cause)
                        }
                        trySend(response)
                    }
        } catch (e: Exception) {
            trySend(Resources.Error(e.cause))
            e.printStackTrace()
        }
        awaitClose {
            snapshotStateListener?.remove()
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
        maxPeople: String,

        onComplete:(Boolean)-> Unit,
    ) {
        val documentId=activitiesRef.document().id
        val activity=ActivityModel(id=documentId,title,date,location,maxPeople,userName.toString())
        activitiesRef.document(documentId).set(activity).addOnCompleteListener {
                result -> onComplete.invoke(result.isSuccessful)
        }
    }

    fun deleteActivity(activityId:String,onComplete:(Boolean)-> Unit){

        activitiesRef.document(activityId)
            .delete().addOnCompleteListener {
                onComplete.invoke(it.isSuccessful)
            }

    }

    fun updateActivity(){

    }
}


sealed class Resources<T>(val data: T? = null, val throwable: Throwable? = null) {


    class Loading<T> : Resources<T>()
    class Success<T>(data: T?) : Resources<T>(data = data)
    class Error<T>(throwable: Throwable?) : Resources<T>(throwable = throwable)

}
package com.example.houseynative.di

import com.example.houseynative.domain.use_case.*
import com.example.houseynative.domain.use_case.UseCases
import com.example.houseynative.repository.*
import com.example.houseynative.repository.activityrepository.ActivityRepository
import com.example.houseynative.repository.activityrepository.ActivityRepositoryImpl
import com.example.houseynative.repository.authrepository.AuthRepository
import com.example.houseynative.repository.authrepository.AuthRepositoryImpl
import com.example.houseynative.repository.searchactivityrepository.SearchActivityRepository
import com.example.houseynative.repository.searchactivityrepository.SearchActivityRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth=FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideActivitiesRef(
        db: FirebaseFirestore
    ) = db.collection("activities")

    @Provides
    fun provideActivityRepository(
        activitiesRef: CollectionReference
    ): ActivityRepository = ActivityRepositoryImpl(activitiesRef)

    @Provides
    fun provideUseCases(
        repo: ActivityRepository
    ) = UseCases(
        getActivities = GetActivities(repo),
        addActivity = AddActivity(repo),
        deleteActivity = DeleteActivity(repo)

    )
    @Provides
    fun provideSearchActivityRepository(
        activitiesQuery: Query
    ): SearchActivityRepository = SearchActivityRepositoryImpl(activitiesQuery)

}


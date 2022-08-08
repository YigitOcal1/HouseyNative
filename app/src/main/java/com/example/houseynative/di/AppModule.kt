package com.example.houseynative.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseFirestore()= Firebase.firestore

    @Provides
    fun provideActivitiesQuery(
        database:FirebaseFirestore
    )=database.collection("activities").orderBy("title")

    @Provides
    fun provideActivityListRepository(
        activitiesQuery:Query
    ):Activit

}
package com.example.houseynative.repository

import com.google.firebase.firestore.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchActivityRepository @Inject constructor(
    private var activitiesQuery: Query
) {
}
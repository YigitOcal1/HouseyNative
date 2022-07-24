package com.example.houseynative.view.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.houseynative.components.BottomNavigationBar
import com.example.houseynative.components.HouseyAppBar
import com.example.houseynative.navigation.HouseyScreens
import com.example.houseynative.view.home.FloatingActionButtonContent
import com.example.houseynative.view.home.HomePageContent

@Composable
fun ProfileScreen(navController: NavController){
    Scaffold(topBar = {
        HouseyAppBar(title = "Housey", navController = navController)
    }, floatingActionButton = {

    }, bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {

        }

    }}
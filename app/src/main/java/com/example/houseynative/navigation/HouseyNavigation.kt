package com.example.houseynative.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.houseynative.view.MainSplashScreen
import com.example.houseynative.view.RegisterScreen
import com.example.houseynative.view.createactivity.CreateActivityScreen
import com.example.houseynative.view.home.HomeScreen
import com.example.houseynative.view.login.LoginScreen
import com.example.houseynative.view.profile.ProfileScreen
import com.example.houseynative.view.searchactivity.SearchActivityScreen

@Composable
fun HouseyNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HouseyScreens.MainSplashScreen.name) {
        composable(HouseyScreens.MainSplashScreen.name) {
            MainSplashScreen(navController = navController)
        }
        composable(HouseyScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(HouseyScreens.RegisterScreen.name) {
            RegisterScreen(navController = navController)
        }
        composable(HouseyScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(HouseyScreens.CreateActivityScreen.name) {
            CreateActivityScreen(navController = navController)
        }
        composable(HouseyScreens.SearchActivityScreen.name) {
            SearchActivityScreen(navController = navController)
        }
        composable(HouseyScreens.ProfileScreen.name) {
            ProfileScreen(navController = navController)
        }
    }
}
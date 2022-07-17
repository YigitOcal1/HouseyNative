package com.example.houseynative.navigation

import okhttp3.Route

enum class HouseyScreens {

    MainSplashScreen,
    LoginScreen,
    RegisterScreen,
    HomeScreen,
    CreateActivityScreen,
    SearchActivityScreen,
    ProfileScreen;
    companion object{
        fun fromRoute(route: String?):HouseyScreens
        =when(route?.substringBefore(delimiter = "/")){
            MainSplashScreen.name->MainSplashScreen
            LoginScreen.name->LoginScreen
            RegisterScreen.name->RegisterScreen
            HomeScreen.name->HomeScreen
            CreateActivityScreen.name->CreateActivityScreen
            SearchActivityScreen.name->SearchActivityScreen
            ProfileScreen.name->ProfileScreen
            null->HomeScreen
            else->throw  IllegalAccessException("Route is not correct")
        }
    }

}
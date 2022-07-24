package com.example.houseynative.navigation.navigationbar

import com.example.houseynative.navigation.HouseyScreens

sealed class NavRoutes(val route:String) {

    object Home:NavRoutes(HouseyScreens.HomeScreen.name)
    object Search: NavRoutes(HouseyScreens.SearchActivityScreen.name)
    object Profile: NavRoutes(HouseyScreens.ProfileScreen.name)
}
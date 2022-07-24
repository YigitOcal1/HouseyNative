package com.example.houseynative.navigation.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.example.houseynative.navigation.HouseyScreens

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = HouseyScreens.HomeScreen.name
        ),
        BarItem(
            title = "Aktivite Arama",
            image = Icons.Filled.Search,
            route = HouseyScreens.SearchActivityScreen.name
        ),
        BarItem(
            title = "Profil",
            image = Icons.Filled.Person,
            route = HouseyScreens.ProfileScreen.name
        )
    )
}
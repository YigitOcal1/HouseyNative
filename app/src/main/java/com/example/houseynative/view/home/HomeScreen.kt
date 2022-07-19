package com.example.houseynative.view.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.houseynative.navigation.HouseyScreens

@Composable
fun HomeScreen(navController: NavController){


    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Housey", style = MaterialTheme.typography.h4, color = Color.Red.copy(alpha = 0.55f))
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            
            Button(onClick = {
                navController.navigate(HouseyScreens.CreateActivityScreen.name)
            }, modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(), shape = RectangleShape) {
                Text(text = "Aktivite Oluştur")
            }
            
        }
    }
}
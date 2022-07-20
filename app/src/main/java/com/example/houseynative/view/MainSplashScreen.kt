package com.example.houseynative.view

import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import com.example.houseynative.navigation.HouseyScreens

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


@Composable
fun MainSplashScreen(navController: NavController){

    val scale= remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(760, easing = {
            OvershootInterpolator(8f).getInterpolation(it)
        }))
        delay(2500L)

        //if user is logged in stay in the session
        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(HouseyScreens.LoginScreen.name)
        }
        else{
            navController.navigate(HouseyScreens.HomeScreen.name)
        }

    }

    Surface(modifier = Modifier
        .padding(16.dp)
        .size(350.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp,
            color = Color.LightGray)

    ) {
        Column(modifier = Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "Housey", style = MaterialTheme.typography.h2, color = Color.Blue.copy(alpha = 0.65f))
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "\"Gerçek bir aktivite deneyimi\"",
                style = MaterialTheme.typography.h5,
                color=Color.DarkGray
            )
        }

    }
}
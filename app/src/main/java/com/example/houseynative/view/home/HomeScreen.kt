package com.example.houseynative.view.home


import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.houseynative.R
import com.example.houseynative.model.ActivityModel
import com.example.houseynative.navigation.HouseyScreens

@Composable
fun HomeScreen(navController: NavController) {


    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {

        Scaffold(topBar = {
            HouseyAppBar(title = "Housey", navController = navController)
        }, floatingActionButton = {
            FloatingActionButtonContent {

            }
        }) {
            Surface(modifier = Modifier.fillMaxSize()) {
                HomePageContent(navController = navController)
            }

        }

        /*Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Button(onClick = {
                navController.navigate(HouseyScreens.CreateActivityScreen.name)
            }, modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(), shape = RectangleShape) {
                Text(text = "Aktivite Oluştur")
            }

        }*/
    }
}

@Composable
fun HouseyAppBar(title: String, showProfile: Boolean = true, navController: NavController) {
    TopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showProfile) {
                //custom image eklemek için
                //Image(painter = painterResource(id = R.drawable.), contentDescription ="" )
                Icon(
                    imageVector = Icons.Default.PlayArrow, contentDescription = "housey logo",
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                )
            }
            Text(
                text = title,
                color = Color(0xFF232946),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),

                )
        }
    }, actions = {}, backgroundColor = Color.Transparent, elevation = 0.dp)
}

@Composable
fun FloatingActionButtonContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = { onTap() },
        shape = RoundedCornerShape(40.dp),
        backgroundColor = Color(0xFF232946)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Aktivite oluştur",
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun TitleSection(modifier: Modifier = Modifier, label: String) {

    Surface(modifier = modifier.padding(start = 6.dp, top = 1.dp)) {
        Column {
            Text(
                text = label,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left
            )
        }
    }
}

@Composable
fun HomePageContent(navController: NavController) {
    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Şu anda katıldığınız \n"+"aktiviteleri bulunmaktadır.")
        }
    }
}


@Composable
fun JoinedActivities(activities: List<ActivityModel>, navController: NavController) {

}


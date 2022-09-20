package com.example.houseynative.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
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
        FloatingActionButtonContent {
            navController.navigate(HouseyScreens.HomeScreen.name)
        }
    }, bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
        ProfileScreenContent(navController = navController)
        }

    }}

@Composable
fun ProfileScreenContent(navController: NavController){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = 4.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserImage(modifier = Modifier.size(160.dp))
                Divider(thickness = 2.dp)
                Text(
                    "Kullanıcı adı",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Oluşturmuş olduğunuz aktiviteler",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primaryVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column() {
                    Text(
                        text = "Katılmış olduğunuz aktiviteler",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primaryVariant
                    )
                }

            }
        }
    }
}
@Composable
private fun UserImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(8.dp),
        shape = CircleShape,
        elevation = 3.dp
    ) {
        Image(
            painter = rememberImagePainter(data = "https://www.woolha.com/media/2020/03/eevee.png"),
            contentDescription = "profileImage"
        )
    }
}
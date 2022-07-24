package com.example.houseynative.view.home


import android.media.Image
import android.telecom.Call
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.houseynative.R
import com.example.houseynative.components.HouseyAppBar
import com.example.houseynative.components.TitleSection
import com.example.houseynative.model.ActivityModel
import com.example.houseynative.navigation.HouseyScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


@Composable
fun HomeScreen(navController: NavController) {


    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {

        Scaffold(topBar = {
            HouseyAppBar(title = "Housey", navController = navController)
        }, floatingActionButton = {
            FloatingActionButtonContent {
            navController.navigate(HouseyScreens.SearchActivityScreen.name)
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
fun FloatingActionButtonContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = { onTap() },
        shape = RoundedCornerShape(40.dp),
        backgroundColor = Color(0xFF232946)
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Aktivite arama",
            tint = MaterialTheme.colors.onPrimary
        )
    }
}


@Composable
fun HomePageContent(navController: NavController) {
    val listofActivities = listOf(
        ActivityModel("qwewq", "deneme", "1231", "asdadqewq", 1, "qwewqdas"),
        ActivityModel("ewqqwewq", "deneme", "1231", "asdadqewq", 1, "qwewqdas"),
        ActivityModel("qweqdwqdwq", "deneme", "1231", "asdadqewq", 1, "qwewqdas"),
        ActivityModel("qwezcxzczxwq", "deneme", "1231", "asdadqewq", 1, "qwewqdas"),

        )
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()) {
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
    } else {
        "User bulunamadı"
    }

    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Şu anda katılmış olduğunuz \n" + "şu aktiviteleriniz bulunmaktadır;")
            Spacer(modifier = Modifier.fillMaxWidth(0.65f))
            Column {
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(HouseyScreens.ProfileScreen.name)
                        }
                        .size(50.dp), tint = MaterialTheme.colors.secondaryVariant)
                Text(
                    text = currentUserName!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    maxLines = 1,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }

        }
        CreatedActivities(activities = listOf(), navController = navController)
        TitleSection(label = "Oluşturmuş olduğunuz aktiviteler")

        ActivityListArea(listofActivities = listofActivities, navController = navController)
    }
}

@Composable
fun ActivityListArea(listofActivities: List<ActivityModel>, navController: NavController) {
    HorizontalScrollableComponent(listofActivities) {
        //todo detay ekranı
    }
}

@Composable
fun HorizontalScrollableComponent(
    listofActivities: List<ActivityModel>,
    onCardPressed: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(240.dp)
            .horizontalScroll(scrollState)
    ) {
        for (activity in listofActivities) {
            ListCard(activity) {
                onCardPressed(it)
            }
        }
    }
}

@Preview
@Composable
fun ListCard(
    activity: ActivityModel = ActivityModel("asd", "asd", "sdafas", "qweq", 22, "wefd"),
    onPressDetails: (String) -> Unit = {}
) {

    val context = LocalContext.current
    val resources = context.resources

    val displayMetrics = resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp
    Card(
        shape = RoundedCornerShape(30.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(18.dp)
            .height(240.dp)
            .width(200.dp)
            .clickable { onPressDetails.invoke(activity.title.toString()) }
    ) {
        Column(
            modifier = Modifier
                .width(
                    screenWidth.dp - (spacing * 2),
                )
                .height(300.dp), horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = rememberImagePainter(data = "https://www.woolha.com/media/2020/03/eevee.png"),
                    contentDescription = "activityimage",
                    modifier = Modifier
                        .height(70.dp)
                        .width(70.dp)
                        .padding(2.dp)
                )
                Spacer(modifier = Modifier.width(55.dp))
                Column(
                    modifier = Modifier.padding(top = 15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date Icon",
                        // = Modifier.padding(bottom = 1.dp)
                    )
                    ActivityDate(date = "11/11/2022")
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",

                        )
                    ActivityLocation(location = "Ankara")
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Location Icon",

                        )
                    ActivityMaxPeople(maxpeople = 5)
                }
            }
            Text(
                text = activity.title.toString(), modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis
            )
            Text(
                text = activity.ownername.toString(),
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.caption
            )
        }
    }

}

@Composable
fun ActivityMaxPeople(maxpeople: Int) {
    Surface(
        modifier = Modifier
            .height(32.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(75.dp),
        elevation = 5.dp,
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(2.dp)) {

            Text(text = maxpeople.toString(), style = MaterialTheme.typography.subtitle1)
        }
    }
}


@Composable
fun ActivityLocation(location: String) {
    Surface(
        modifier = Modifier
            .height(32.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(75.dp),
        elevation = 5.dp,
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(2.dp)) {

            Text(text = location, style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun ActivityDate(date: String = "11/11/2022") {
    Surface(
        modifier = Modifier
            .height(36.dp)
            .padding(2.dp),
        shape = RoundedCornerShape(75.dp),
        elevation = 5.dp,
        color = Color.White
    ) {
        Text(text = date, style = MaterialTheme.typography.subtitle1)

    }
}


@Composable
fun CreatedActivities(activities: List<ActivityModel>, navController: NavController) {
    ListCard()
}


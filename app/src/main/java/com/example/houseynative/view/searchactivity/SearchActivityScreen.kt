package com.example.houseynative.view.searchactivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.houseynative.components.BottomNavigationBar
import com.example.houseynative.components.HouseyAppBar
import com.example.houseynative.components.InputField
import com.example.houseynative.model.ActivityModel
import com.example.houseynative.navigation.HouseyScreens
import com.example.houseynative.viewmodel.SearchScreenViewModel
import kotlinx.coroutines.job


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchActivityScreen(navController: NavController,
                         viewModel: SearchScreenViewModel) {


    Scaffold(topBar = {
        HouseyAppBar(
            title = "Aktivite Arama",
            icon = Icons.Default.ArrowBack,
            navController = navController,
            showProfile = false
        ) {
            navController.navigate(HouseyScreens.HomeScreen.name)
        }
    }, bottomBar = { BottomNavigationBar(navController = navController) }) {
        Surface() {
            Column {
                SearchForm(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {

                searchQuery->viewModel.getSearchActivity(searchText = searchQuery)
                }
                }
                Spacer(modifier = Modifier.height(16.dp))
                ActivityList(navController)
            }
        }
    }



@Composable
fun ActivityList(navController: NavController) {
    val listOfActivities = listOf(
        //dummy data
        ActivityModel("qwewq", "deneme", "1231", "asdadqewq", "1", "qwewqdas"),
        ActivityModel("ewqqwewq", "deneme", "1231", "asdadqewq", "1", "qwewqdas"),
        ActivityModel("qweqdwqdwq", "deneme", "1231", "asdadqewq", "1", "qwewqdas"),
        ActivityModel("qwezcxzczxwq", "deneme", "1231", "asdadqewq", "1", "qwewqdas"),

        )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items = listOfActivities) { activity ->
            ActivityRow(activity, navController)
        }
    }

}

@Composable
fun ActivityRow(activity: ActivityModel, navController: NavController) {
    Card(modifier = Modifier
        .clickable { }
        .fillMaxWidth()
        .height(100.dp)
        .padding(4.dp), shape = RectangleShape, elevation = 8.dp) {
        Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.Top) {

            Image(
                painter = rememberImagePainter(data = "https://www.woolha.com/media/2020/03/eevee.png"),
                contentDescription = "activityimage"
            )
            Column() {
                Text(text = activity.title.toString(), overflow = TextOverflow.Ellipsis)
                Text(text = activity.date.toString(), overflow = TextOverflow.Ellipsis)
                Text(text = activity.location.toString(), overflow = TextOverflow.Ellipsis)
                Text(text = activity.maxPeople.toString(), overflow = TextOverflow.Ellipsis)
                Text(text = activity.ownerName.toString(), overflow = TextOverflow.Ellipsis)

            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Aktivite Ara",
    onSearch: (String) -> Unit = {}
) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()

        }

        InputField(valueState = searchQueryState,
            labelId = "Arama",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })

    }


}

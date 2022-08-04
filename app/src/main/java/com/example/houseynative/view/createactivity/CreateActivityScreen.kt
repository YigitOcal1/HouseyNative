package com.example.houseynative.view.createactivity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.houseynative.components.*
import com.example.houseynative.navigation.HouseyScreens
import com.example.houseynative.view.searchactivity.ActivityList
import com.example.houseynative.view.searchactivity.SearchForm
import com.example.houseynative.viewmodel.CreateActivityScreenViewModel
import com.example.houseynative.viewmodel.LoginScreenViewModel

@Composable
fun CreateActivityScreen(
    navController: NavController,
    viewModel: CreateActivityScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {


    Scaffold(
        topBar = {
            HouseyAppBar(
                title = "Aktivite Oluştur",
                icon = Icons.Default.ArrowBack,
                navController = navController,
                showProfile = false
            ) {
                navController.navigate(HouseyScreens.HomeScreen.name)
            }
        },
    ) {
        Surface() {
            Column {
                CreateActivityForm(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    loading = false,
                ) { title, date, location, maxpeople ->
                    viewModel.createActivity(
                        title = title,
                        date = date,
                        location = location,
                        maxPeople = maxpeople,

                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateActivityForm(
    modifier: Modifier = Modifier, loading: Boolean = false,
    hint: String = "Aktivite Oluştur",
    onCreateActivity: (String, String, String, String) -> Unit = { title, date, location, maxpeople -> }
) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val titleQueryState = rememberSaveable { mutableStateOf("") }
        val dateQueryState = rememberSaveable { mutableStateOf("") }
        val locationQueryState = rememberSaveable { mutableStateOf("") }
        val maxpeopleQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
            titleQueryState.value.trim().isNotEmpty()
            dateQueryState.value.trim().isNotEmpty()
            locationQueryState.value.trim().isNotEmpty()
            maxpeopleQueryState.value.trim().isNotEmpty()

        }

        InputField(valueState = titleQueryState,
            labelId = "Başlık",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                //onCreateActivity(titleQueryState.value.trim())
                titleQueryState.value = ""
                keyboardController?.hide()
            })
        InputField(valueState = dateQueryState,
            labelId = "Tarih",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                //onCreateActivity(dateQueryState.value.trim())
                dateQueryState.value = ""
                keyboardController?.hide()
            })
        InputField(valueState = locationQueryState,
            labelId = "Konum",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                //onCreateActivity(locationQueryState.value.trim())
                locationQueryState.value = ""
                keyboardController?.hide()
            })
        InputField(valueState = maxpeopleQueryState,
            labelId = "Maksimum katılabilecek kişi sayısı",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                // onCreateActivity(
                //titleQueryState.value.trim(),
                //dateQueryState.value.trim(),
                //locationQueryState.value.trim(),
                //maxpeopleQueryState.value.trim()
                //)
                maxpeopleQueryState.value = ""
                keyboardController?.hide()
            })
        SubmitButtonActivity(
            textId = "aktivite oluştur",
            loading = loading,
            validinputs = valid
        ) {
            onCreateActivity(
                titleQueryState.value.trim(),
                dateQueryState.value.trim(),
                locationQueryState.value.trim(),
                maxpeopleQueryState.value.trim()
            )
            keyboardController?.hide()
        }

    }
}
package com.example.houseynative.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.houseynative.navigation.navigationbar.NavBarItems

@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String, enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()


    OutlinedTextField(value = passwordState.value, onValueChange = {
        passwordState.value = it
    },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,

            ),
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) }
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icons.Default.Close
    }
}


@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value, onValueChange = {
            valueState.value = it
        },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 14.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction

    )
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
fun HouseyAppBar(
    title: String,
    icon: ImageVector? = null,
    showProfile: Boolean = true,
    navController: NavController,
    onBackArrowClicked: () -> Unit = {}
) {
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
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "geri dön",
                    tint = Color(0xFF232946),
                    modifier = Modifier.clickable { onBackArrowClicked.invoke() }
                )

            }
            Spacer(modifier = Modifier.width(40.dp))
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
fun BottomNavigationBar(navController: NavController){
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}
@Composable
fun SubmitButton(textId: String, loading: Boolean, validinputs: Boolean,onClick:()->Unit) {

    Button(onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = !loading&&validinputs,
        shape= CircleShape
    ) {
        if(loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}
@Composable
fun SubmitButtonActivity(textId: String, loading: Boolean, validinputs: Boolean,onClick:()->Unit) {

    Button(onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),

        shape= CircleShape
    ) {
        if(loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}
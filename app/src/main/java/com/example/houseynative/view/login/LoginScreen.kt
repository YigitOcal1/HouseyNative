package com.example.houseynative.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.houseynative.components.EmailInput
import com.example.houseynative.components.PasswordInput
import com.example.houseynative.navigation.HouseyScreens
import com.example.houseynative.viewmodel.LoginScreenViewModel

@Composable
fun LoginScreen(navController: NavController,viewModel: LoginScreenViewModel= androidx.lifecycle.viewmodel.compose.viewModel()){

    val showLoginForm= rememberSaveable {
        mutableStateOf(value = true)
    }

    Surface(modifier = Modifier.fillMaxSize() ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Housey",
                style = MaterialTheme.typography.h3,
                color = Color.Blue.copy(alpha = 0.5f)
            )
            if (showLoginForm.value) {
                UserForm(loading = false, isCreateAccount = false) { email, password ->
                    //firebase
                    viewModel.logIn(email = email, password = password) {
                        navController.navigate((HouseyScreens.HomeScreen.name))
                    }
                }
            } else {
                UserForm(loading = false, isCreateAccount = true) { email, password ->
                    viewModel.createUserWithEmailAndPassword(email = email, password = password) {
                        navController.navigate(HouseyScreens.HomeScreen.name)
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(top = 50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text = if (showLoginForm.value) "Register" else "Login"
                Text(text = "Hesabınız yoksa buraya tıklayarak kayıt olabilirsiniz")
            }
            Row(
                modifier = Modifier.padding(top = 50.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text = if (showLoginForm.value) "Register" else "Login"
                Text(text,
                    modifier = Modifier
                        .clickable {
                            showLoginForm.value = !showLoginForm.value
                        }
                        .padding(start = 8.dp),
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primaryVariant)
            }
        }
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(loading:Boolean=false,
             isCreateAccount:Boolean=false,
             onDone:(String,String)-> Unit= {email,pwd ->}){
    val email= rememberSaveable {
        mutableStateOf("")
    }
    val password= rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility= rememberSaveable {
        mutableStateOf(false)
    }
    val passwordFocusRequest=FocusRequester.Default
    val  keyboardController=LocalSoftwareKeyboardController.current
    val valid=remember(email.value,password.value){

        email.value.trim().isNotEmpty()&& password.value.trim().isNotEmpty()

    }

    val modifier= Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())
    Column(
        modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(isCreateAccount) Text(text = "Please enter a valid email address and password", modifier = Modifier.padding(4.dp))else Text(text = "")
        EmailInput(emailState = email, enabled = !loading, onAction = KeyboardActions {
            passwordFocusRequest.requestFocus()
        })
        PasswordInput(modifier=Modifier.focusRequester(passwordFocusRequest),
            passwordState=password,
            labelId="Password",
            enabled=!loading,
            passwordVisibility=passwordVisibility,
            onAction= KeyboardActions {
                if(!valid) return@KeyboardActions
                onDone(email.value.trim(),password.value.trim())
            })
        SubmitButton(
            textId=if(isCreateAccount)"Create a new account" else "Login",
            loading=loading,
            validinputs=valid
        ){
            onDone(email.value.trim(),password.value.trim())
            keyboardController?.hide()
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
        shape= CircleShape) {
        if(loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}
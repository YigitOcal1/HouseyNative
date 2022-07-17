package com.example.houseynative.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordInput(modifier: Modifier,
                  passwordState: MutableState<String>,
                  labelId: String, enabled: Boolean,
                  passwordVisibility: MutableState<Boolean>,
                  onAction: KeyboardActions = KeyboardActions.Default,) {
    val visualTransformation=if(passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()


    OutlinedTextField(value = passwordState.value, onValueChange ={
        passwordState.value=it
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
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility)}
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible=passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value= !visible }) {
        Icons.Default.Close
    }
}



@Composable
fun EmailInput(modifier: Modifier = Modifier,
               emailState: MutableState<String>,
               labelId:String="Email",
               enabled:Boolean=true,
               imeAction: ImeAction = ImeAction.Next,
               onAction: KeyboardActions = KeyboardActions.Default ){
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
fun InputField(modifier: Modifier = Modifier, valueState: MutableState<String>,
               labelId: String, enabled: Boolean, isSingleLine:Boolean=true, keyboardType: KeyboardType = KeyboardType.Text,
               imeAction: ImeAction = ImeAction.Next, onAction: KeyboardActions = KeyboardActions.Default){
    OutlinedTextField(value = valueState.value, onValueChange = {
        valueState.value=it},
        label = { Text(text=labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 14.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
        ,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType, imeAction = imeAction)

    )}
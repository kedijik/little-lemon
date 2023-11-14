package com.kedijik.littlelemon


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Form(navController: NavController){
    val TextFieldColours = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor  = colors.onPrimary,
        unfocusedBorderColor  = colors.onSecondary,

    )
    val curr = LocalContext.current
    val prefs = curr.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
    val isLoggedIn = prefs.getBoolean(Constants.LOGIN,false)
    Column(Modifier.padding(vertical = 25.dp)){
        Text(text= if (isLoggedIn) "Profile information" else "Personal Information", fontWeight = FontWeight.Bold, fontSize = 24.sp )
        val firstName = remember { mutableStateOf(if(isLoggedIn) {prefs.getString(Constants.FIRSTNAME,"")?:""} else "") }
        val lastName = remember { mutableStateOf(if(isLoggedIn) {prefs.getString(Constants.LASTNAME,"")?:""} else "")       }
        val email = remember { mutableStateOf(if(isLoggedIn) {prefs.getString(Constants.EMAIL,"")?:""} else "")       }
        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxHeight(.6f) ){
            Column { //This column is just to group label and the box
                Text(text = "First name",
                    modifier = Modifier.padding(bottom = 4.dp),
                    textAlign = TextAlign.Start)
                if(isLoggedIn){ // If we are logged in, show a text field of same styling with the data
                    Box( contentAlignment = Alignment.CenterStart,
                        modifier=Modifier.height(56.dp).fillMaxWidth(.8f)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                1.dp,
                                colors.onSecondary,
                                shape = RoundedCornerShape(12.dp),
                            )){
                        Text(text= firstName.value, modifier = Modifier.padding(horizontal = 16.dp))}
                }
                else { // If we are logged out, actually have an input field.
                    OutlinedTextField(
                        value = firstName.value,
                        modifier = Modifier.fillMaxWidth(.8f),
                        colors = TextFieldColours,
                        shape = RoundedCornerShape(12.dp) ,
                        onValueChange = { firstName.value = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))}}
            Column {
                Text(text = "Last name",
                    modifier = Modifier.padding(bottom = 4.dp),
                    textAlign = TextAlign.Start)
                if(isLoggedIn){
                    Box( contentAlignment = Alignment.CenterStart,
                        modifier=Modifier.height(56.dp).fillMaxWidth(.8f)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                1.dp,
                                colors.onSecondary,
                                shape = RoundedCornerShape(12.dp),
                            )){
                        Text(text= lastName.value, modifier = Modifier.padding(horizontal = 16.dp))}
                }
                else {
                    OutlinedTextField(
                        value = lastName.value,
                        modifier = Modifier.fillMaxWidth(.8f),
                        colors = TextFieldColours,
                        shape = RoundedCornerShape(12.dp) ,
                        onValueChange = { lastName.value = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))}}



              Column {
                  Text(text = "Email",
                      modifier = Modifier.padding(bottom = 4.dp),
                      textAlign = TextAlign.Start)
                  if(isLoggedIn){
                      Box( contentAlignment = Alignment.CenterStart,
                          modifier=Modifier.height(56.dp).fillMaxWidth(.8f)
                              .clip(RoundedCornerShape(12.dp))
                              .border(
                                  1.dp,
                                  colors.onSecondary,
                                  shape = RoundedCornerShape(12.dp),
                              )){
                          Text(text= email.value, modifier = Modifier.padding(horizontal = 16.dp))}
                  }
                  else {
                      OutlinedTextField(
                          value = email.value,
                          modifier = Modifier.fillMaxWidth(.8f),
                          colors = TextFieldColours,
                          shape = RoundedCornerShape(12.dp) ,
                          onValueChange = { email.value = it },
                          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))}}
        }

        OutlinedButton( onClick={
            if(!isLoggedIn){
                if(firstName.value.isBlank()  || lastName.value.isBlank() || email.value.isBlank() ){
                    Toast.makeText(curr,"Registration unsuccessful. Please enter all the data.", Toast.LENGTH_SHORT).show()
                }
                else if (!"^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}\$".toRegex().matches(email.value)){
                    Toast.makeText(curr,"Registration unsuccessful. Please check email format.", Toast.LENGTH_SHORT).show()
                }
                else {
                prefs.edit()
                    .putBoolean(Constants.LOGIN, true)
                    .putString(Constants.FIRSTNAME,firstName.value)
                    .putString(Constants.LASTNAME,lastName.value)
                    .putString(Constants.EMAIL, email.value)
                    .commit()
                    Toast.makeText(curr,"Registration successful.", Toast.LENGTH_LONG).show()
                navController.navigate(Profile.route)}}
            else {
                prefs.edit()
                    .clear()
                    .putBoolean(Constants.LOGIN, false)
                    .commit()
                Toast.makeText(curr,"Successfully logged out.", Toast.LENGTH_LONG).show()
                navController.navigate(Onboarding.route)

            }

        }, modifier = Modifier
            .fillMaxWidth(.8f)
            .fillMaxHeight(.25f),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = colors.secondaryVariant))
        {Text(if(isLoggedIn) "Log Out" else "Register", fontWeight = FontWeight.Bold, fontSize = 16.sp)}

    }
}


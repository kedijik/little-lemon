package com.kedijik.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Onboarding( navController: NavController) {

    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()){


            Text("Let's get to know you",
                textAlign = TextAlign.Center,
                modifier = Modifier.background(MaterialTheme.colors.surface).fillMaxHeight(0.2f).fillMaxWidth().wrapContentHeight(),
                fontSize = 36.sp,
                color = Color.White)

        Form(navController)



   }
}


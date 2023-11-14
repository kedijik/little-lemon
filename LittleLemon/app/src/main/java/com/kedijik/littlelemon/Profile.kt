package com.kedijik.littlelemon

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Profile(navController: NavController){
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()){

        Box(modifier = Modifier.fillMaxHeight(0.2f).fillMaxWidth().wrapContentHeight())


        Form(navController)



    }

    }
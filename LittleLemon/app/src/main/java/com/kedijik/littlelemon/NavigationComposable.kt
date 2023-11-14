package com.kedijik.littlelemon

import android.content.Context.MODE_PRIVATE
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun NavigationComposable(){
    val curr = LocalContext.current
    val prefs = curr.getSharedPreferences(Constants.PREFS, MODE_PRIVATE)
    val isLoggedIn = prefs.getBoolean("isLoggedIn",false)
    val navController = rememberNavController()
    NavHost(navController, startDestination=(if (isLoggedIn)  "Profile" else "Onboarding")){
        composable(Home.route)  { Home() }
        composable(Profile.route){ Profile(navController)}
        composable(Onboarding.route){ Onboarding(navController)}

        }

    }

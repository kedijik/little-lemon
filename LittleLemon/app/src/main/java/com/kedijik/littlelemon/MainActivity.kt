package com.kedijik.littlelemon


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kedijik.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)


        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                val isLoggedIn = remember { mutableStateOf(prefs.getBoolean(Constants.LOGIN,false)) }
                navController.addOnDestinationChangedListener { controller, destination, args ->
                    isLoggedIn.value=prefs.getBoolean(Constants.LOGIN,false)
                }
              Scaffold(

                topBar= {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.1f)
                            .background(MaterialTheme.colors.background),
                        contentAlignment = Alignment.Center


                    ) {
                        Image(
                            alignment = Alignment.Center,
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.6f)


                        )}
                        if(isLoggedIn.value){Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(.1f).padding(end = 12.dp),

                            contentAlignment = Alignment.CenterEnd
                        ) {
                              Button(  modifier= Modifier.width(48.dp) , contentPadding = PaddingValues(
                                  start = 0.dp,
                                  top = 0.dp,
                                  end = 0.dp,
                                  bottom = 0.dp,
                              ),elevation = ButtonDefaults.elevation(
                                  defaultElevation = 0.dp,
                                  pressedElevation = 0.dp,
                                  disabledElevation = 0.dp,
                              ) ,onClick = { navController.navigate(Profile.route) } , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, )){Image(

                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile",
                            contentScale = ContentScale.Crop,
                                  modifier = Modifier.size(48.dp)
                                      .clip(CircleShape)




                        )}}


                }
                    }
              ){innerPadding -> Box(modifier = Modifier.padding(innerPadding)){NavigationComposable(navController)}}

            }
        }
    }
}



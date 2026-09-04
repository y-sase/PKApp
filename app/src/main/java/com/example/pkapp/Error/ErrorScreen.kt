package com.example.pkapp.Error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.pkapp.ui.theme.ScreenRoute
import com.example.pkapp.viewmodel.PKViewModel

@Composable
fun ErrorScreen(
    navController:NavController,
    viewModel: PKViewModel,
    onClick: () -> Unit

){
    //LaunchedEffect(Unit) {




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            imageVector = Icons.Default.Error,
            contentDescription = "Error",
            tint = Color(0xFFE57373)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Error;("
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            Button(

                onClick = {


                    navController.navigate(
                        ScreenRoute.LoadingScreen.route
                    )


                },


                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE57373)
                )
            ) {
                Text(
                    text = "Retry",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(

                onClick = {


                    navController.navigate(
                        ScreenRoute.LoadingScreen.route
                    )


                },


                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB3E5FC)
                )
            ) {
                Text(
                    text = "Home",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                )
            }


        }
    }

}

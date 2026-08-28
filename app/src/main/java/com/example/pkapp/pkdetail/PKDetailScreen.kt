package com.example.pkapp.pkdetail

import android.R.attr.id
import android.R.attr.name

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pkapp.ui.theme.Favorite
import com.example.pkapp.viewmodel.PKViewModel

@Composable
fun PKDetailScreen(
    viewModel: PKViewModel,
) {
    LaunchedEffect(Unit) {

        viewModel.loadPokemonDetail(1)

    }
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .heightIn(100.dp)
            .border(
                width = 3.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Favorite(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(vertical = 70.dp)
                .padding(horizontal = 16.dp)
        )


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "No.${viewModel.PKId}",
                color = Color.Black,
                fontSize = 30.sp,
            )
            Text(
                text = viewModel.PKName,
                color = Color.Black,
                fontSize = 60.sp,
            )

            AsyncImage(//AsyncImage がURLから画像をダウンロードして表示
                model = viewModel.PKSprites.front_default,
                contentDescription = "ポケモン",
                modifier = Modifier
                    .size(300.dp),
                contentScale = ContentScale.Crop//枠いっぱいに表示
            )


            Text(
                text = "高さ：${viewModel.PKHeight* 10}cm\n" +
                        "重さ：${viewModel.PKWeight/ 10.0}kg\n" +
                        "タイプ：${viewModel.PKTypes}",
                color = Color.Black,
                fontSize = 20.sp,
                lineHeight = 43.sp

            )

            Spacer(modifier = Modifier.height(50.dp))
            Button(

                onClick = {

                    /*
                    navController.navigate(
                        ScreenRoute.LoadingScreen.route
                    )

                     */

                },


                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB3E5FC)
                )
            )
            {
                Text(
                    text = "Back",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = viewModel.errorMessage,
                color = Color.Red
            )

        }

    }
}
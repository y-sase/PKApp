package com.example.pkapp.pkdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontFamily.Companion.Serif
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pkapp.ui.theme.Favorite
import com.example.pkapp.viewmodel.PKViewModel

//詳細画面部分

@Composable
fun PKDetailScreen(
    viewModel: PKViewModel,
    navController: NavController,
    onClick: () -> Unit,
) {
    LaunchedEffect(Unit) {

        viewModel.loadPokemonDetail(viewModel.PKId, onSuccess = {}, onError = {})

    }
    Scaffold(
        containerColor = Color.LightGray
    ) { innerPadding -> //Scaffoldとセット
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(top = 200.dp)
                .clip(RoundedCornerShape(70.dp))
                .background(Color(0xFF9E9E9E))
                //.heightIn(100.dp)
                /*
                .border(
                    width = 3.dp, color = Color(0xFF90A4AE), shape = RoundedCornerShape(30.dp)
                )

                 */
                .padding(innerPadding)

        )
            Spacer(modifier = Modifier.height(200.dp))
            Favorite(
                viewModel = viewModel,
                pokemonId = viewModel.PKId,
                modifier = Modifier
                    //.align(Alignment.TopEnd)
                    .padding(vertical = 30.dp)
                    .padding(horizontal = 16.dp)
            )


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                AsyncImage(//AsyncImage がURLから画像をダウンロードして表示
                    model = viewModel.PKSprites.frontDefault,
                    contentDescription = "ポケモン",
                    modifier = Modifier.size(300.dp),
                    contentScale = ContentScale.Crop//枠いっぱいに表示
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                   modifier = Modifier
                       .offset(y = (-70).dp)
                       .fillMaxWidth()
                       .padding(start = 25.dp),
                    //horizontalArrangement = Arrangement.End

                ) {
                    Text(
                        text = "No.${viewModel.PKId}",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = viewModel.PKName,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                    )

                }


                Row(
                    modifier = Modifier
                    .offset(y = (-40).dp)

                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .size(width = 160.dp, height = 80.dp)

                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                modifier = Modifier
                                    .size(60.dp),
                                imageVector = Icons.Default.Height,
                                contentDescription = "Height",
                                tint = Color(0xFFF50057)
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                        Column() {
                            Text(
                                text = "高さ",
                                color = Color.Black,
                                fontSize = 15.sp,
                                lineHeight = 43.sp

                            )
                            Text(
                                modifier = Modifier
                                    .offset(y = (-20).dp)
                                ,text = "${viewModel.PKHeight / 10.0}m",
                                color = Color.Black,
                                fontSize = 30.sp,
                                // lineHeight = 50.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 4.sp

                            )
                        }

                    }
                    }
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .size(width = 160.dp, height = 80.dp)
                    ) {

                        Row() {

                            Icon(
                                modifier = Modifier
                                    .size(60.dp),
                                imageVector = Icons.Default.Height,
                                contentDescription = "Height",
                                tint = Color(0xFFF50057)
                            )


                            Column() {
                                Text(
                                    text = "高さ",
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    lineHeight = 43.sp

                                )
                                Text(

                                    text = "${viewModel.PKHeight / 10.0}m",
                                    color = Color.Black,
                                    fontSize = 30.sp,
                                    // lineHeight = 50.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 4.sp

                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(-30.dp))
                Row(
                    modifier = Modifier

                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .size(width = 160.dp, height = 80.dp)

                    ) {}
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .size(width = 160.dp, height = 80.dp)
                    ) {}
                }

                Text(
                    text = "高さ：${viewModel.PKHeight / 10.0}m\n" + "重さ：${viewModel.PKWeight / 10.0}kg\n" + "タイプ：${viewModel.PKTypes}",
                    color = Color.Black,
                    fontSize = 20.sp,
                    lineHeight = 43.sp

                )

                Spacer(modifier = Modifier.height(50.dp))
                Button(

                    onClick = {
                        navController.navigate("pklist_screen") {
                            popUpTo("pklist_screen") {
                                inclusive = false
                            }
                        }
/*
                        navController.navigate(
                            "loading_list"
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
                ) {
                    Text(
                        text = "Back",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

            }


    }
}


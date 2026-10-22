package com.example.pkapp.feature.pklist.pkdetail


import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MergeType
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.XXX.RadarGraph
import com.example.pkapp.ui.theme.Favorite
import com.example.pkapp.viewmodel.PKViewModel
import androidx.compose.ui.graphics.Brush
import com.example.pkapp.feature.pklist.components.getTypeColor




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
    val mainType = viewModel.PKTypes
        .split(" / ")
        .firstOrNull() ?: ""
    Scaffold(
        containerColor = getTypeColor(
            mainType
        ),//Color(0xFF9E9E9E),


        ) { innerPadding -> //Scaffoldとセット

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            getTypeColor(mainType),
                            Color(0xFFF5F5F5)
                        )
                    )
                )
        ) {


            Column(
                modifier = Modifier
                    // .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Favorite(
                    viewModel = viewModel, pokemonId = viewModel.PKId, modifier = Modifier
                        //.align(Alignment.TopEnd)
                        .padding(vertical = 30.dp)
                        .padding(horizontal = 16.dp)
                )
                Box(
                    modifier = Modifier//.fillMaxWidth()
                        .padding(top = 30.dp)
                    // .offset(y = (-140).dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(
                                //top = 200.dp,
                                bottom = 0.dp
                            )
                            .clip(
                                RoundedCornerShape(
                                    70.dp//topStart = 70.dp, topEnd = 70.dp
                                )
                            )
                            .background(Color(0xFFF5F5F5))
                            .height(1150.dp)


                            /*
                    .border(
                        width = 3.dp, color = Color(0xFF90A4AE), shape = RoundedCornerShape(30.dp)
                    )

                     */.padding(innerPadding)

                    )
                    //Spacer(modifier = Modifier.height(200.dp))


                    Column(
                        modifier = Modifier
                            // .verticalScroll(rememberScrollState())
                            .fillMaxWidth()
                            .padding(top = 0.dp)
                            .offset(y = (-150).dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        //Spacer(modifier = Modifier.height(50.dp))

                        AsyncImage(//AsyncImage がURLから画像をダウンロードして表示
                            model = viewModel.PKSprites.frontDefault,
                            contentDescription = "ポケモン",
                            modifier = Modifier.size(300.dp),
                            contentScale = ContentScale.Crop//枠いっぱいに表示
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Row(
                            modifier = Modifier
                                .offset(y = (-70).dp)
                                .fillMaxWidth()
                                .padding(start = 25.dp),
                            //horizontalArrangement = Arrangement.End

                        ) {
                            Text(
                                text = "No.${viewModel.PKId}",
                                color = Color(0xFF37474F),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = viewModel.PKName,
                                color = Color(0xFF37474F),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )

                        }


                        Row(
                            modifier = Modifier.offset(y = (-40).dp)

                        ) {
                            //高さ
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
                                        modifier = Modifier.size(60.dp),
                                        imageVector = Icons.Default.Height,
                                        contentDescription = "Height",
                                        tint = Color(0xFFF48FB1)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "高さ",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            lineHeight = 43.sp

                                        )
                                        Text(
                                            modifier = Modifier.offset(y = (-10).dp),
                                            text = "${viewModel.PKHeight / 10.0}m",
                                            color = Color.Black,
                                            fontSize = 18.sp,
                                            // lineHeight = 50.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 4.sp

                                        )
                                    }

                                }
                            }
                            //重さ
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
                                            .size(58.dp)
                                            .padding(horizontal = 10.dp),
                                        imageVector = Icons.Default.Scale,
                                        contentDescription = "Weight",
                                        tint = Color(0xFF90CAF9)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "重さ",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            lineHeight = 43.sp

                                        )
                                        Text(
                                            modifier = Modifier.offset(y = (-10).dp),
                                            text = "${viewModel.PKWeight / 10.0}kg",
                                            color = Color.Black,
                                            fontSize = 18.sp,
                                            // lineHeight = 50.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 5.sp

                                        )

                                    }

                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(-30.dp))
                        Row(

                            modifier = Modifier.offset(y = (-20).dp)

                        ) {
                            //タイプ
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White)
                                    .size(width = 350.dp, height = 80.dp)

                            ) {

                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {


                                    Icon(
                                        modifier = Modifier.size(60.dp),
                                        imageVector = Icons.Default.MergeType,
                                        contentDescription = "Type",
                                        tint = Color(0xFF1DE9B6)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = "タイプ",
                                        color = Color.Black,
                                        fontSize = 15.sp,
                                        lineHeight = 43.sp

                                    )
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(

                                        text = "${viewModel.PKTypes}",
                                        color = Color.Black,
                                        fontSize = 22.sp,
                                        // lineHeight = 50.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 4.sp

                                    )


                                }
                            }

                        }

                        //チャート
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .size(width = 350.dp, height = 350.dp)

                        ) {
                            val resultMap = mapOf(
                                "HP ${viewModel.PKHp}" to viewModel.PKHp.toFloat(),//MPAndroidChartは基本的に Float を使う
                                "こうげき ${viewModel.PKAttack}" to viewModel.PKAttack.toFloat(),
                                "ぼうぎょ ${viewModel.PKDefense}" to viewModel.PKDefense.toFloat(),
                                "とくこう ${viewModel.PKSpecialAttack}" to viewModel.PKSpecialAttack.toFloat(),
                                "とくぼう ${viewModel.PKSpecialDefense}" to viewModel.PKSpecialDefense.toFloat(),
                                "すばやさ ${viewModel.PKSpeed}" to viewModel.PKSpeed.toFloat()
                            )

                            RadarGraph(resultMap)

                        }

                        Spacer(modifier = Modifier.height(30.dp))
                        Row {
                            //Note機能
                            //var memo by rememberSaveable { mutableStateOf("") }
                            val focusManager = LocalFocusManager.current

                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White)
                                    .size(width = 350.dp, height = 270.dp),
                                contentAlignment = Alignment.Center

                            ) {
                                Column {
                                    //Note機能
                                    //noteFumction()

                                    OutlinedTextField(
                                        value = viewModel.memo,
                                        onValueChange = {
                                            viewModel.memo = it
                                        },
                                        label = { Text("メモ") },
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .size(width = 320.dp, height = 170.dp),
                                        // .fillMaxWidth()
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            imeAction = ImeAction.Done // キーボードの「完了」ボタン
                                        ),
                                        keyboardActions = KeyboardActions(
                                            onDone = {
                                                focusManager.clearFocus()
                                            })
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Button(

                                        onClick = {
                                            viewModel.saveMemo()
                                        },
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    ) {
                                        Text("保存")
                                    }
                                }
                            }

                        }


                        Spacer(modifier = Modifier.height(30.dp))
                        Button(

                            onClick = {
                                navController.navigate("pklist_screen") {
                                    popUpTo("pklist_screen") {
                                        inclusive = false
                                    }
                                }


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
                                text = "← 戻る",
                                color = Color.White,
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                    }

                }
            }

        }
    }
}


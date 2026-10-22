package com.example.pkapp.SearchBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pkapp.feature.pklist.components.TypeMark
import com.example.pkapp.feature.pklist.components.changeTypeName
import com.example.pkapp.feature.pklist.components.getTypeColor
import com.example.pkapp.viewmodel.PKViewModel


@Composable
fun TypeFilterBar(
    viewModel: PKViewModel, navController: NavController, onClose: () -> Unit
) {


    Box(

        modifier = Modifier
            .padding(horizontal = 5.dp)
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .background(Color.White)
            .border(2.dp, Color(0xFF90A4AE))
            .padding(horizontal = 15.dp)
            .padding(vertical = 15.dp)
            .background(Color.White)
        //.heightIn(100.dp)
    ) {
        Row {

            Column {


                var typeid = 0

                repeat(10) {
                    typeid++
                    val currentTypeId = typeid


                    Box(contentAlignment = Alignment.Center) {

                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(45.dp)
                                .background(Color.White)
                                .clip(RoundedCornerShape(10.dp))
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    viewModel.toggletype(currentTypeId)
                                }, contentAlignment = Alignment.Center
                        ) {
                            val TypeSet =
                                currentTypeId in viewModel.typeIds //今のポケモンIDが、お気に入り一覧の中に含まれているか？

                            val typeName = changeTypeName(
                                viewModel.typeList[currentTypeId - 1].name
                            )


                            Box(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(35.dp)
                                    .background(
                                        if (TypeSet) getTypeColor(typeName)
                                        else Color.White, shape = RoundedCornerShape(10.dp)
                                    )
                                    .clip(RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center

                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    TypeMark(
                                        typeName = viewModel.typeList[currentTypeId - 1].name,
                                        isSelected = TypeSet
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = changeTypeName(
                                            viewModel.typeList[currentTypeId - 1].name
                                        ),
                                        color = if (TypeSet) Color.White
                                        else Color.Black,
                                        fontSize = 18.sp,
                                    )
                                }
                            }


                        }

                    }

                    Spacer(modifier = Modifier.height(15.dp))
                }
            }

            Spacer(modifier = Modifier.width(40.dp))

            Column {


                var typeid2 = 10

                repeat(9) {
                    typeid2++

                    val currentTypeId2 = typeid2



                    Box(contentAlignment = Alignment.Center) {

                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(45.dp)
                                .background(Color.White)
                                .clip(RoundedCornerShape(10.dp))
                                .border(
                                    width = 2.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    viewModel.toggletype(currentTypeId2)
                                }, contentAlignment = Alignment.Center
                        ) {
                            val TypeSet =
                                currentTypeId2 in viewModel.typeIds //今のポケモンIDが、お気に入り一覧の中に含まれているか？

                            val typeName = changeTypeName(
                                viewModel.typeList[currentTypeId2 - 1].name
                            )


                            Box(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(
                                        35.dp
                                    )
                                    .background(
                                        if (TypeSet) getTypeColor(typeName)
                                        else Color.White, shape = RoundedCornerShape(10.dp)
                                    )
                                    .clip(RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    TypeMark(
                                        typeName = viewModel.typeList[currentTypeId2 - 1].name,
                                        isSelected = TypeSet
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = changeTypeName(
                                            viewModel.typeList[currentTypeId2 - 1].name
                                        ),
                                        color = if (TypeSet) Color.White
                                        else Color.Black,
                                        fontSize = 18.sp,
                                    )
                                }


                            }

                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.offset(x = -30.dp)
                ) {
                    Button(
                        onClick = {
                            viewModel.resettype()
                        },
                        modifier = Modifier
                            .height(35.dp)
                            .width(95.dp)
                            //.border(2.dp, Color.Black,shape = RoundedCornerShape(3.dp))
                            .clip(RoundedCornerShape(3.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB3E5FC)
                        ),

                        ) {
                        Text(
                            text = "リセット",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    Button(
                        onClick = {

                            viewModel.loadPokemonByTypes(
                                onSuccess = {},
                                onError = { navController.navigate("error_screen") })

                            onClose()
                        },
                        modifier = Modifier
                            .height(35.dp)
                            .width(95.dp)
                            //.border(2.dp, Color.Black,shape = RoundedCornerShape(3.dp))
                            .clip(RoundedCornerShape(3.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE57373)
                        ),

                        ) {
                        Text(
                            text = "検索",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }


            }
        }
    }
}


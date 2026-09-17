package com.example.pkapp.SearchBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.pkapp.pklist.PKListScreen
import com.example.pkapp.viewmodel.PKViewModel

@Composable
fun TypeFilterBar(
    viewModel: PKViewModel,
    typelist :  TypeListItem
    ){
    Box(
        modifier = Modifier
           // .padding(horizontal = 16.dp)
            .padding(vertical = 5.dp)
            .background(Color.White)
            .heightIn(200.dp)
            .border(
                width = 3.dp,
                color = Color.Black,
                //shape = RoundedCornerShape(10.dp)
            )
    ){
        Row() {

            Column() {


                var typeid = viewModel.

                repeat(10) {
                    Row() {

                        IconButton(
                            onClick = {
                                viewModel.toggletype(typeid)
                            }
                        ) {
                            val TypeSet =
                                typeid in viewModel.typeIds //今のポケモンIDが、お気に入り一覧の中に含まれているか？
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Default.Done,
                                contentDescription = "TypeFilter",
                                tint =
                                    if (TypeSet) Color.Black
                                    else Color.White


                            )

                        }
                        Icon(
                            imageVector = Icons.Default.CropSquare,
                            modifier = Modifier.size(25.dp),
                            contentDescription = "TypeFilter",
                            tint = Color.Black
                        )
                        Text(
                            text = TypeListItem(typeid).name,
                            color = Color.Black,
                            fontSize = 20.sp,
                        )
                    }
                    typeid++


                }
            }

            Column() {


                var typeid2 = 11

                repeat(9) {
                    Row() {

                        IconButton(
                            onClick = {
                                viewModel.toggletype(typeid2)
                            }
                        ) {
                            val TypeSet =
                                typeid2 in viewModel.typeIds //今のポケモンIDが、お気に入り一覧の中に含まれているか？
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Default.Done,
                                contentDescription = "TypeFilter",
                                tint =
                                    if (TypeSet) Color.Black
                                    else Color.White


                            )

                        }
                        Icon(
                            imageVector = Icons.Default.CropSquare,
                            modifier = Modifier.size(25.dp),
                            contentDescription = "TypeFilter",
                            tint = Color.Black
                        )
                        Text(
                            text = TypeListItem(typeid2).name,
                            color = Color.Black,
                            fontSize = 20.sp,
                        )
                    }
                    typeid2++


                }
                Row() {
                    Button(
                        onClick = {
                            viewModel.resettype()
                        },
                        modifier = Modifier
                            .height(25.dp)
                            .width(75.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB3E5FC)
                        )
                    ) {
                        Text(
                            text = "リセット",
                            color = Color.White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                        )
                }
                    Spacer(modifier = Modifier.width(20.dp))

                    Button(
                        onClick = {
                            println(viewModel.typeIds)
                        },
                        modifier = Modifier
                            .height(25.dp)
                            .width(75.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE57373)
                        )
                    ) {
                        Text(
                            text = "検索",
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
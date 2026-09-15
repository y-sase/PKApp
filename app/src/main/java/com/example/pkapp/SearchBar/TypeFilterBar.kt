package com.example.pkapp.SearchBar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

@Composable
fun TypeFilterBar(viewModel: ViewModel){
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
        Row{
            Column() {
                Row() {

                    repeat(9) {
                        val typeSetName: String
                        Text(
                            text = "typeSetName",
                            color = Color.Black,
                            fontSize = 20.sp,
                        )
                        IconButton(
                            onClick = {
                                /*TODO*/
                            }
                        ) {
                            val TypeSet =
                                pokeType in viewModel.PKTypes //今のポケモンIDが、お気に入り一覧の中に含まれているか？
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
                    }

                    val typeSetName: String
                    Text(
                        text = "ノーマル",
                        color = Color.Black,
                        fontSize = 20.sp,
                    )
                }
            }
        }

    }
}
package com.example.pkapp.pklist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon

import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.pkapp.model.PokemonListItem

@Composable
fun PKThumbnail(
    id: Int,
    name: String,
    pokemonimageinList:PokemonListItem
    //sprites: Sprites,
    // onClick: (Photo) -> Unit//クリックされてUnitをかえす
) {


    Box(

        //.clickable { onClick(photo) },//クリックされたときonClickを呼び出す、引数photo

    ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(
                            top = 16.dp,
                            end = 16.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(34.dp),
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = Color(0xFFFF4081)
                    )

                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        modifier = Modifier
                            .size(40.dp),
                        contentDescription = "Favorite",
                        tint = Color.Gray
                    )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,//左寄せ(Rowの時)
                    modifier = Modifier.fillMaxWidth()
                ) {

                AsyncImage(//AsyncImage がURLから画像をダウンロードして表示
                    model = pokemonimageinList.imageUrl,
                    contentDescription = "ポケモン",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 16.dp),
                    contentScale = ContentScale.Crop//枠いっぱいに表示
                )
                    /*
                    Image(
                        painter = painterResource(id = R.drawable.monster03),
                        contentDescription = "ポケモン",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(start = 16.dp),
                    )

                    */


                        Spacer(modifier = Modifier.width(50.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "No.$id",
                                color = Color.Black,
                                fontSize = 16.sp,
                            )
                            Text(
                                text = name,
                                color = Color.Black,
                                fontSize = 26.sp,
                            )
                        }


                }
            }


    }



/*
@Composable
fun AsyncImage(model: imageUrl, contentDescription: description, modifier: Modifier) {
    TODO("Not yet implemented")
}

 */
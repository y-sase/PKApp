package com.example.pkapp.pklist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Label
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pkapp.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon

import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder

@Composable
fun PKThumbnail(

    //photo: Photo,
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
                /*
                AsyncImage(
                    model = photo.imageUrl,
                    contentDescription = photo.description,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .padding(start = 16.dp),
                )*/
                Image(
                    painter = painterResource(id = R.drawable.monster03),
                    contentDescription = "ポケモン",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .padding(start = 16.dp),
                )

                Spacer(modifier = Modifier.width(50.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "No.",
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                    Text(
                        text = "NAME",
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
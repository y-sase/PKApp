package com.example.pkapp.pklist.components

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pkapp.model.PokemonListItem
import com.example.pkapp.ui.theme.Favorite
import com.example.pkapp.viewmodel.PKViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun PKThumbnail(
    id: Int,
    name: String,
    pokemonimageinList: PokemonListItem,
    onClick: () -> Unit,//クリックされてUnitをかえす
    viewModel: PKViewModel
) {

    var jpName by remember {
        mutableStateOf("")
    }
    LaunchedEffect(name) {
        jpName = viewModel.getJpName(name)
    }

    Box(
        modifier = Modifier.clickable {
                onClick()
            }

    ) {
        Favorite(
            viewModel = viewModel,
            pokemonId = id,
            modifier = Modifier.align(Alignment.TopEnd),
        )


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
                    text = if (jpName.isNotEmpty()) {
                        jpName
                    } else {
                        name
                    },
                    color = Color.Black,
                    fontSize = 26.sp,
                )
            }


        }
    }
}


package com.example.pkapp.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import com.example.pkapp.viewmodel.PKViewModel

@Composable
fun Favorite(
    viewModel: PKViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier

            .padding(
                top = 16.dp, end = 16.dp
            ), contentAlignment = Alignment.Center
    ) {

        IconButton(
            onClick = {
                viewModel.toggleFavorite(viewModel.PKId)
            }
        ) {
            val isFavorite = viewModel.PKId in viewModel.favoriteIds //今のポケモンIDが、お気に入り一覧の中に含まれているか？
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
            tint =
                if (isFavorite)Color(0xFFFF4081)
                else Color.White


        )

        }
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            modifier = Modifier.size(40.dp),
            contentDescription = "Favorite",
            tint = Color.Gray
        )
    }
}
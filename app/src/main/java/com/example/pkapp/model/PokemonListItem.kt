package com.example.pkapp.model

import androidx.lifecycle.viewmodel.compose.viewModel

data class PokemonListItem(
    val name: String,
    val url: String,
    val listJpName: String = "",
    ) {
    val id: Int
        get() = url//URL取りだす
            .trimEnd('/')//末尾の / を削除
            .substringAfterLast('/')//最後の / より後ろを取り出す
            .toInt(//文字列を Int に変換
            )


    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
package com.example.pkapp.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.api.PokemonJpNameResponse
import com.example.pkapp.api.PokemonJpTypeResponse
import com.example.pkapp.viewmodel.PKViewModel
import okhttp3.internal.threadName

/*
data class names(
    val name: String,
    val language: String
)

data class language(
    val name: String
)
*/


fun ChangeLanguageName(
    detail: PokemonDetailResponse,
    jpnameResponse: PokemonJpNameResponse,
): String{  //最終的にStringを返す。
    var jpName = detail.name


    jpName = jpnameResponse.names
        .first{it.language.name == "ja-hrkt"}//first: 条件に一致した最初の1件を返す（Listから１件とるからstringで返せる）
        .name

    return jpName
}
fun ChangeLanguageType(
    detail: PokemonDetailResponse,
    jptypeResponse: PokemonJpTypeResponse
): List<String>{  //最終的にStringを返す。


    return jptypeResponse.names
        .filter { it.language.name == "ja-hrkt" }//filter:条件に合うものだけ残す
        .map { it.name }//map:必要な項目だけ取り出す
}
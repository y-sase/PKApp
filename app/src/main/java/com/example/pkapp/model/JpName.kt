package com.example.pkapp.model

import com.example.pkapp.data.api.PokemonJpNameResponse
import com.example.pkapp.data.api.PokemonJpTypeResponse


fun getJapaneseName(

    jpnameResponse: PokemonJpNameResponse,
): String {  //最終的にStringを返す。


    var jpName =
        jpnameResponse.names.first { it.language.name == "ja-hrkt" }//first: 条件に一致した最初の1件を返す（Listから１件とるからstringで返せる）
            .name

    return jpName
}

fun changeLanguageType(

    jptypeResponse: PokemonJpTypeResponse
): List<String> {  //最終的にStringを返す。


    return jptypeResponse.names.filter { it.language.name == "ja-hrkt" }//filter:条件に合うものだけ残す
        .map { it.name }//map:必要な項目だけ取り出す
}
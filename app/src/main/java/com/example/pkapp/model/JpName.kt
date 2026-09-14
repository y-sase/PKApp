package com.example.pkapp.model

import androidx.compose.remote.creation.dsl.first
import com.example.pkapp.data.api.PokemonJpNameResponse
import com.example.pkapp.data.api.PokemonJpTypeResponse
import com.example.pkapp.data.api.repository.PKRepository


fun getJapaneseName(

    jpnameResponse: PokemonJpNameResponse,
): String {  //最終的にStringを返す。


    var jpName =
        jpnameResponse.names.first { it.language.name == "ja-hrkt" }//first: 条件に一致した最初の1件を返す（Listから１件とるからstringで返せる）
            .name

    return jpName
}

suspend fun getTypeList(
    repository: PKRepository,
    id: Int,
): List<String> {  //最終的にStringを返す。

    val responsedetail = repository.getPokemonDetail(id)

    return responsedetail.types.map { typeInfo ->//typeInfoは今処理中の1件
        val typeId = typeInfo
            .type.url
            .trimEnd('/')
            .substringAfterLast('/')//最後の / より後ろだけ取得
            .toInt()//文字列を数値に変換 String->Int

        val responsejptype = repository.getPokemonJpType(typeId)

        responsejptype.names
            .first { it.language.name == "ja-hrkt" }//filter:条件に合うものだけ残す
            .name

    }
}
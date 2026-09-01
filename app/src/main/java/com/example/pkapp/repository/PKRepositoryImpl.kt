package com.example.pkapp.repository

import android.R.attr.name
import com.example.pkapp.api.PKApi
import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.api.PokemonJpNameResponse
import com.example.pkapp.api.PokemonJpTypeResponse
import com.example.pkapp.api.PokemonListResponse

class PKRepositoryImpl (
    private val api: PKApi    //api を受け取る
) : PKRepository {        // Interfaceを実装する
    override suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse {//Interfaceで約束した  実装します
        return api.getPokemonDetail(id)//APIを呼ぶ
    }
    override suspend fun getPokemonList(): PokemonListResponse {//Interfaceで約束した getAdvice を実装します
        return api.getPokemonList()//APIを呼ぶ
    }

    override suspend fun getPokemonJpName(
        name: String
    ): PokemonJpNameResponse {//Interfaceで約束した  実装します
        return api.getPokemonJpName(name)//APIを呼ぶ
    }
    override suspend fun getPokemonJpType(
        id: Int
    ): PokemonJpTypeResponse {//Interfaceで約束した  実装します
        return api.getPokemonJpType(id)//APIを呼ぶ
    }
}


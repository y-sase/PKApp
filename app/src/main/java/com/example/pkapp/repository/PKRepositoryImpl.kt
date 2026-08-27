package com.example.pkapp.repository

import com.example.pkapp.api.PKApi
import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.api.PokemonListResponse

class PKRepositoryImpl (
    private val api: PKApi    //api を受け取る
) : PKRepository {        // Interfaceを実装する
    override suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse {//Interfaceで約束した getAdvice を実装します
        return api.getPokemonDetail(id)//APIを呼ぶ
    }
    override suspend fun getPokemonList(): PokemonListResponse {//Interfaceで約束した getAdvice を実装します
        return api.getPokemonList()//APIを呼ぶ
    }
}


package com.example.pkapp.api

class PKRepositoryImpl (
    private val api: PKApi    //api を受け取る
) : PKRepository {        // Interfaceを実装する
    override suspend fun getPokemon(
        id: Int
    ): PokemonResponse {//Interfaceで約束した getAdvice を実装します
        return api.getPokemon(id)//APIを呼ぶ
    }
}
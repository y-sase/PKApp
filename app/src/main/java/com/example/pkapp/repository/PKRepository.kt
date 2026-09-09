package com.example.pkapp.repository

import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.api.PokemonJpNameResponse
import com.example.pkapp.api.PokemonJpTypeResponse
import com.example.pkapp.api.PokemonListResponse

interface PKRepository {
    suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse
    suspend fun getPokemonList(
    ): PokemonListResponse

    /*詳細画面部分
    suspend fun getPokemonJpName(
        name: String
    ):PokemonJpNameResponse

    suspend fun getPokemonJpType(
        id: Int
    ): PokemonJpTypeResponse

     */
}
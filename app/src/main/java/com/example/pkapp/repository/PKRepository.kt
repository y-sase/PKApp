package com.example.pkapp.repository

import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.api.PokemonListResponse

interface PKRepository {
    suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse
    suspend fun getPokemonList(
    ): PokemonListResponse
}
package com.example.pkapp.api

interface PKRepository {
    suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse
    suspend fun getPokemonList(
    ): PokemonListResponse
}
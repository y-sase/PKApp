package com.example.pkapp.api

interface PKRepository {
    suspend fun getPokemon(
        id: Int
    ): PokemonResponse
}
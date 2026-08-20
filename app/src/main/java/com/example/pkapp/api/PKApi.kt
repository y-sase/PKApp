package com.example.pkapp.api

import retrofit2.http.GET
import retrofit2.http.Path
interface PKApi {
    @GET("{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): PokemonResponse
}
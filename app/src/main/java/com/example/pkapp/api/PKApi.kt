package com.example.pkapp.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface PKApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 1351,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse
    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): PokemonDetailResponse

    /*
    詳細画面部分

    @GET("pokemon-species/{name}")
    suspend fun getPokemonJpName(
        @Path("name") name: String
    ): PokemonJpNameResponse

    @GET("type/{id}")
    suspend fun getPokemonJpType(
        @Path("id") id: Int
    ): PokemonJpTypeResponse

     */
}
package com.example.pkapp.data.api.repository

import com.example.pkapp.common.NetworkResponse
import com.example.pkapp.data.api.PokemonDetailResponse
import com.example.pkapp.data.api.PokemonJpNameResponse
import com.example.pkapp.data.api.PokemonJpTypeResponse
import com.example.pkapp.data.api.PokemonListResponse
import com.example.pkapp.data.api.PokemonListbyTypeResponse
import com.example.pkapp.data.api.TypeListResponse

interface PKRepository {

    suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse

    suspend fun getPokemonList(
    ): NetworkResponse<PokemonListResponse>

    suspend fun getPokemonListbyType(
        id: Int
    ): NetworkResponse<PokemonListbyTypeResponse>

    suspend fun getTypeList(
    ): TypeListResponse

    suspend fun getPokemonJpName(
        name: String
    ): PokemonJpNameResponse

    suspend fun getPokemonJpType(
        id: Int
    ): PokemonJpTypeResponse
}
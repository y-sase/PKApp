package com.example.pkapp.repository

import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.api.PokemonJpNameResponse
import com.example.pkapp.api.PokemonJpTypeResponse
import com.example.pkapp.api.PokemonListResponse
import com.example.pkapp.api.TypeListResponse
import com.example.pkapp.common.NetworkResponse

interface PKRepository {
    suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse
    suspend fun getPokemonList(
    ): NetworkResponse<PokemonListResponse>

    suspend fun getTypeList(
    ):TypeListResponse
    suspend fun getPokemonJpName(
        name: String
    ):PokemonJpNameResponse

    suspend fun getPokemonJpType(
        id: Int
    ): PokemonJpTypeResponse
}
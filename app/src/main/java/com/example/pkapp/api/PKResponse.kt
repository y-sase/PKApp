package com.example.pkapp.api

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<TypeSlot>
)


data class PokemonListResponse(
    val count: Int,//総件数
    val results: List<PokemonListItem>
)
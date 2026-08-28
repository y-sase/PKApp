package com.example.pkapp.api

import com.example.pkapp.model.Language
import com.example.pkapp.model.PokemonListItem
import com.example.pkapp.model.Sprites
import com.example.pkapp.model.TypeName
import com.example.pkapp.model.TypeSlot


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

data class PokemonJpNameResponse(
    val names: List<PokemonName>
)

data class PokemonName(
    val name: String,
    val language: Language
)

data class  PokemonJpTypeResponse(
    val names: List<TypeName>
)
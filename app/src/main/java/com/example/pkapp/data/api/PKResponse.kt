package com.example.pkapp.data.api


import com.example.pkapp.SearchBar.TypeListItem
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
    val types: List<TypeSlot>,
    val species: Species,
    /*
    val hp:Int,
    val attack:Int,
    val defense:Int,
    val specialAttack:Int,
    val specialDefense:Int,
    val speed:Int

     */
    val stats: List<StatsListItem>
)

data class StatsListItem(
    /*
    val hp:Int,
    val attack:Int,
    val defense:Int,
    val specialAttack:Int,
    val specialDefense:Int,
    val speed:Int
     */
    val base_stat:Int,
    val stat: Stat
)
data class Stat(
    val name: String
)


data class Species(
    val name: String,
    val url: String
)
data class PokemonListResponse(
    val count: Int,//総件数
    val results: List<PokemonListItem>
)
data class PokemonListbyTypeResponse(
    val pokemon: List<TypePokemonItem>
)

data class TypePokemonItem(
    val pokemon: PokemonListItem
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
data class TypeListResponse(
    val count: Int,//総件数
    val results: List<TypeListItem>
)
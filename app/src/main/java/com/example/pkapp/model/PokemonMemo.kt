package com.example.pkapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_memo")
data class PokemonMemo(

    @PrimaryKey
    val pokemonId: Int,

    val memo: String
)
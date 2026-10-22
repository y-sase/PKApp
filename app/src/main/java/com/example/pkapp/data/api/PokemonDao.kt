package com.example.pkapp.data.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pkapp.model.PokemonMemo

@Dao
interface PokemonMemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMemo(memo: PokemonMemo)

    @Query("SELECT * from pokemon_memo WHERE pokemonId = :pokemonId")
    suspend fun getMemo(pokemonId: Int): PokemonMemo?
}
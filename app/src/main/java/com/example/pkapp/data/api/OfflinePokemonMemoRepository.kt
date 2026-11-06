package com.example.pkapp.data.api

import PokemonMemoRepository
import com.example.pkapp.model.PokemonMemo

class OfflinePokemonMemoRepository(private val pokemonMemoDao: PokemonMemoDao) : PokemonMemoRepository {

    override suspend fun saveMemo(
        memo: PokemonMemo
    ) {
        pokemonMemoDao.saveMemo(memo)
    }

    override suspend fun getMemo(
        pokemonId: Int
    ): PokemonMemo? {
        return pokemonMemoDao.getMemo(pokemonId)
    }
}
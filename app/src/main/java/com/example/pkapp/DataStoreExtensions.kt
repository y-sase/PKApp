package com.example.pkapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings"
)

class DataStoreManager(private val context: Context) {


    companion object {
        val FAVORITES = stringSetPreferencesKey("favorites")
    }

    suspend fun addFavorite(pokemonId: Int) {
        context.dataStore.edit { prefs ->
            val favorites = prefs[FAVORITES] ?: emptySet()
            prefs[FAVORITES] = favorites + pokemonId.toString()
        }
    }

    suspend fun removeFavorite(pokemonId: Int) {
        context.dataStore.edit { prefs ->
            val favorites = prefs[FAVORITES] ?: emptySet()
            prefs[FAVORITES] = favorites - pokemonId.toString()
        }
    }

    fun getFavorites(): Flow<Set<String>> {
        return context.dataStore.data.map { prefs ->
            prefs[FAVORITES] ?: emptySet()
        }
    }
}


package com.example.pkapp.data.api.repository

import com.example.pkapp.api.PKApi
import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.api.PokemonJpNameResponse
import com.example.pkapp.api.PokemonJpTypeResponse
import com.example.pkapp.api.PokemonListResponse
import com.example.pkapp.common.NetworkResponse
=======
import com.example.pkapp.data.api.PKApi
import com.example.pkapp.data.api.PokemonDetailResponse
import com.example.pkapp.data.api.PokemonJpNameResponse
import com.example.pkapp.data.api.PokemonJpTypeResponse
import com.example.pkapp.data.api.PokemonListResponse
>>>>>>> main:app/src/main/java/com/example/pkapp/data/api/repository/PKRepositoryImpl.kt

class PKRepositoryImpl(
    private val api: PKApi    //api を受け取る
) : PKRepository {        // Interfaceを実装する
    override suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse {//Interfaceで約束した  実装します
        return api.getPokemonDetail(id)//APIを呼ぶ
    }

<<<<<<< HEAD:app/src/main/java/com/example/pkapp/repository/PKRepositoryImpl.kt
    override suspend fun getPokemonList(): NetworkResponse<PokemonListResponse> {
        return try {

            val response = api.getPokemonList()

            NetworkResponse.Success(response)

        } catch (e: Exception) {

            NetworkResponse.Failure(
                e.message ?: "通信エラー"
            )
        }
=======
    override suspend fun getPokemonList(): PokemonListResponse {//Interfaceで約束した getAdvice を実装します
        return api.getPokemonList()//APIを呼ぶ
>>>>>>> main:app/src/main/java/com/example/pkapp/data/api/repository/PKRepositoryImpl.kt
    }

    //詳細画面部分
    override suspend fun getPokemonJpName(
        name: String
    ): PokemonJpNameResponse {//Interfaceで約束した  実装します
        return api.getPokemonJpName(name)//APIを呼ぶ
    }

    override suspend fun getPokemonJpType(
        id: Int
    ): PokemonJpTypeResponse {//Interfaceで約束した  実装します
        return api.getPokemonJpType(id)//APIを呼ぶ
    }


}


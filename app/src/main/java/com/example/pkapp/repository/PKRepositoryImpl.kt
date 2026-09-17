package com.example.pkapp.repository

import com.example.pkapp.api.PKApi
import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.api.PokemonJpNameResponse
import com.example.pkapp.api.PokemonJpTypeResponse
import com.example.pkapp.api.PokemonListResponse
import com.example.pkapp.api.TypeListResponse
import com.example.pkapp.common.NetworkResponse

class PKRepositoryImpl(
    private val api: PKApi    //api を受け取る
) : PKRepository {        // Interfaceを実装する
    override suspend fun getPokemonDetail(
        id: Int
    ): PokemonDetailResponse {//Interfaceで約束した  実装します
        return api.getPokemonDetail(id)//APIを呼ぶ
    }

    override suspend fun getPokemonList(): NetworkResponse<PokemonListResponse> {
        return try {

            val response = api.getPokemonList()

            NetworkResponse.Success(response)

        } catch (e: Exception) {

            NetworkResponse.Failure(
                e.message ?: "通信エラー"
            )
        }
    }
    override suspend fun getTypeList(
    ): TypeListResponse {//Interfaceで約束した  実装します
        return api.getTypeList()//APIを呼ぶ
    }

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


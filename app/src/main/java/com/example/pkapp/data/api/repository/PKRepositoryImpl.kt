package com.example.pkapp.data.api.repository

import com.example.pkapp.data.api.PokemonListbyTypeResponse
import com.example.pkapp.data.api.TypeListResponse
import com.example.pkapp.common.NetworkResponse

import com.example.pkapp.data.api.PKApi
import com.example.pkapp.data.api.PokemonDetailResponse
import com.example.pkapp.data.api.PokemonJpNameResponse
import com.example.pkapp.data.api.PokemonJpTypeResponse
import com.example.pkapp.data.api.PokemonListResponse

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

    override suspend fun getPokemonListbyType(
        id: Int): NetworkResponse<PokemonListbyTypeResponse> {
        return try {

            val response = api.getPokemonListbyType(id)

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


package com.example.pkapp.api

import com.example.pkapp.api.Base_Api_url.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitInstance {
    fun providePKApi(): PKApi {
        return Retrofit.Builder()// Retrofit作成開始。Retrofitを初期化。
            .baseUrl(BASE_URL)//テンプレ↓
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(PKApi::class.java)//↑

    }
}
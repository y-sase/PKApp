package com.example.pkapp.common

sealed class NetworkResponse <T> (//<T> はジェネリクスでいろんな型を入れられる
    val data: T? = null,
    val error: String? =null,
){
    class Success<T>(data:T): NetworkResponse<T>(data = data)
    class Failure<T>(error: String) : NetworkResponse<T>(error = error)
    class Loading<T> : NetworkResponse<T>()
}
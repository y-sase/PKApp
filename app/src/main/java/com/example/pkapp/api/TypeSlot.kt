package com.example.pkapp.api

data class TypeSlot (
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)
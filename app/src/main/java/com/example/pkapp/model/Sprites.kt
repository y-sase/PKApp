package com.example.pkapp.model

import com.squareup.moshi.Json

data class Sprites(
    @Json(name = "front_default")
    val frontDefault: String
)
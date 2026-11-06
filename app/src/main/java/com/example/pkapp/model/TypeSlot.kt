package com.example.pkapp.model

import org.intellij.lang.annotations.Language
data class TypeSlot(
    val slot: Int,
    val type: Type
)
data class Type(
    val name: String,
    val url: String
)
data class TypeName(
    val name: String,
    val language: com.example.pkapp.model.Language
)

data class Language(
    val name: String
)
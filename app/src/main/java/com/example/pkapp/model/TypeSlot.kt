package com.example.pkapp.model

import org.intellij.lang.annotations.Language

data class TypeName(
    val name: String,
    val language: Language
)

data class Language(
    val name: String
)
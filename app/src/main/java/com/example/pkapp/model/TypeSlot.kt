package com.example.pkapp.model

import org.intellij.lang.annotations.Language
//詳細画面部分
data class TypeName(
    val name: String,
    val language: Language
)

data class Language(
    val name: String
)
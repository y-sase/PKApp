package com.example.pkapp.feature.pklist.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.AutoFixHigh
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.HotelClass
import androidx.compose.material.icons.filled.LabelImportant
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.NightlightRound
import androidx.compose.material.icons.filled.Pentagon
import androidx.compose.material.icons.filled.PestControl
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.SportsMma
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



fun changeTypeName(typeName: String): String {
    return when (typeName) {
        "normal" -> "ノーマル"
        "fighting" -> "かくとう"
        "flying" -> "ひこう"
        "poison" -> "どく"
        "ground" -> "じめん"
        "rock" -> "いわ"
        "bug" -> "むし"
        "ghost" -> "ゴースト"
        "steel" -> "はがね"
        "fire" -> "ほのお"
        "water" -> "みず"
        "grass" -> "くさ"
        "electric" -> "でんき"
        "psychic" -> "エスパー"
        "ice" -> "こおり"
        "dragon" -> "ドラゴン"
        "dark" -> "あく"
        "fairy" -> "フェアリー"
        "stellar" -> "ステラー"
        else -> typeName
    }
}

//タイプ色
fun getTypeColor(type: String): Color {
    return when (type) {
        "ほのお" -> Color(0xFFFFCC80)
        "みず" -> Color(0xFFB3E5FC)
        "くさ" -> Color(0xFFB9F6CA)
        "でんき" -> Color(0xFFFFF590)
        "こおり" -> Color(0xFFB2EBF2)
        "かくとう" -> Color(0xFFFF8A80)
        "どく" -> Color(0xFFE1BEE7)
        "じめん" -> Color(0xFFBCAAA4)
        "ひこう" -> Color(0xFFB39DDB)
        "エスパー" -> Color(0xFFEF9A9A)
        "むし" -> Color(0xFFDCEDC8)
        "いわ" -> Color(0xFFCFD8DC)
        "ゴースト" -> Color(0xFF9FA8DA)
        "ドラゴン" -> Color(0xFF82B1FF)
        "あく" -> Color(0xFF757575)
        "はがね" -> Color(0xFF80CBC4)
        "フェアリー" -> Color(0xFFF8BBD0)
        else -> Color(0xFFB0BBC5)
    }
}

@Composable
fun TypeMark(
    typeName: String, isSelected: Boolean
) {

    Icon(
        modifier = Modifier.size(24.dp),
        imageVector = when (typeName) {

            "normal" -> Icons.Default.TripOrigin
            "fighting" -> Icons.Default.SportsMma
            "flying" -> Icons.Default.LabelImportant
            "poison" -> Icons.Default.Science
            "ground" -> Icons.Default.Terrain
            "rock" -> Icons.Default.Gavel
            "bug" -> Icons.Default.PestControl
            "ghost" -> Icons.Default.Visibility
            "steel" -> Icons.Default.Pentagon
            "fire" -> Icons.Default.LocalFireDepartment
            "water" -> Icons.Default.WaterDrop
            "grass" -> Icons.Default.Grass
            "electric" -> Icons.Default.FlashOn
            "psychic" -> Icons.Default.Psychology
            "ice" -> Icons.Default.AcUnit
            "dragon" -> Icons.Default.HotelClass
            "dark" -> Icons.Default.NightlightRound
            "fairy" -> Icons.Default.AutoAwesome
            "stellar" -> Icons.Default.AutoFixHigh
            else -> Icons.Default.Help

        },
        contentDescription = typeName,
        tint = if (isSelected) Color.White
        else Color.Black,


        )
}
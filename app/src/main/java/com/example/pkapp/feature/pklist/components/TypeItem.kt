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
        "ほのお","fire" -> Color(0xFFFFCC80)
        "みず","water" -> Color(0xFFB3E5FC)
        "くさ","grass" -> Color(0xFFB9F6CA)
        "でんき","electric" -> Color(0xFFFFF590)
        "こおり","ice" -> Color(0xFFB2EBF2)
        "かくとう","fighting" -> Color(0xFFFF8A80)
        "どく","poison" -> Color(0xFFE1BEE7)
        "じめん","ground" -> Color(0xFFBCAAA4)
        "ひこう","fly" -> Color(0xFFB39DDB)
        "エスパー","psychic" -> Color(0xFFEF9A9A)
        "むし","bug" -> Color(0xFFDCEDC8)
        "いわ","rock" -> Color(0xFFCFD8DC)
        "ゴースト","ghost" -> Color(0xFF9FA8DA)
        "ドラゴン","dragon" -> Color(0xFF82B1FF)
        "あく","dark" -> Color(0xFF757575)
        "はがね","steel" -> Color(0xFF80CBC4)
        "フェアリー","fairy" -> Color(0xFFF8BBD0)
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
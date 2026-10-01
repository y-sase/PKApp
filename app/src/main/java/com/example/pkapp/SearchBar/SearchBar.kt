package com.example.pkapp.SearchBar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    searchText: String,
    onSearchPKChanged: (String) -> Unit,
    onDone: () -> Unit,
    placeholderText: String = "Search...",


    ) {
    var showClearButton by remember { mutableStateOf(false) }//×ボタン
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(
        value = searchText,
        onValueChange = onSearchPKChanged,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,//テキストボックスを選択中（カーソルがある時）の枠線の色
            unfocusedBorderColor = Color.Transparent,//選択していない時の枠線の色
            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White
        ),
        modifier = Modifier
            //.fillMaxWidth()
            .padding(vertical = 16.dp)
            .height(52.dp)
            .width(285.dp)
            .onFocusChanged { focusState ->
                showClearButton = focusState.isFocused
            }
            .focusRequester(focusRequester),

        placeholder = {
            Text(text = placeholderText)
        },
        trailingIcon = {
            IconButton(onClick = { onSearchPKChanged("") }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "close",
                )
            }
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            onDone()
        })
    )


}
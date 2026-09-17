package com.example.pkapp.pklist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pkapp.SearchBar.TypeFilterBar
import com.example.pkapp.pklist.components.PKThumbnail
import com.example.pkapp.viewmodel.PKViewModel
import kotlin.text.toIntOrNull
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp


@Composable
fun PKListScreen(
    viewModel: PKViewModel, navController: NavController, onClick: () -> Unit
) {
    val state = viewModel.state.value
    var showTypeFilter by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {

        viewModel.loadPokemonList(
            onSuccess = {},
            onError = { navController.navigate("error_screen") })

    }

    LaunchedEffect(Unit) {
        viewModel.loadTypeList()
    }


        Scaffold(
            containerColor = Color.LightGray, topBar = {
                Column() {

                Row() {



                    Button(
                        onClick = {
                            showTypeFilter = !showTypeFilter
                        },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .padding(vertical =16.dp)
                            .height(48.dp)
                            .width(90.dp)
                            .border(2.dp, Color.Black)

                    )
                    {
                        Text(
                            text = "タイプ",
                            color = Color.Black,
                        )
                    }

                    Box(//SearchBar(
                //shape = RoundedCornerShape(0.dp),

                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .padding(vertical =16.dp)
                            .height(48.dp)
                            .width(450.dp)
                            .border(2.dp, Color.Black)
                            .background(Color.White)
                ){
                        Text(
                            text = "Search",
                            color = Color.Black,
                            fontSize = 25.sp,
                        )
                    }
                    /*

                                        searchText = viewModel.query,
                                        onSearchPKChanged = {
                                            viewModel.query = it
                                                            },
                                        onDone = {
                                            val id = viewModel.PKName
                                            val name = viewModel.PKId.toIntOrNull()//.toIntOrNull():文字列を Int に変換する。変換できなかったら null を返す

                                            if(id == null || name == null) {
                                                viewModel.errorMessage = "エラー：数字もしくは文字を入力してください"
                                            } else {
                                                viewModel.errorMessage = ""
                                                viewModel.query = id.toString()
                                            }
                                        }
                                    )*/


                }
                    if (showTypeFilter) {

                            TypeFilterBar(
                                viewModel = viewModel,
                            )

                    }

            }
            }) { paddingValues ->
            Column {


                LazyColumn(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    if (state.isLoading) {
                        items(10) {//(viewModel.pokemonList) { pokemon -> //pokemonListからポケモンを1匹ずつ取り出して、pokemonという名前で使う,for文みたいな

                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(vertical = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White)
                                    .heightIn(100.dp)
                                    .fillMaxWidth()
                                    .border(
                                        width = 3.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            ) {
                                //ローディング
                                CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
                            }

                        }

                    } else {

                        items(viewModel.pokemonList) { pokemon ->
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(vertical = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White)
                                    .heightIn(100.dp)
                                    .border(
                                        width = 3.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            ) {
                                PKThumbnail(

                                    id = pokemon.id,
                                    name = pokemon.name,
                                    pokemonimageinList = pokemon,

                                    viewModel = viewModel,

                                    onClick = {//画面遷移
                                        viewModel.PKId = pokemon.id
                                        navController.navigate(
                                            "loading_detail"
                                        )
                                    })
                            }
                        }
                    }

                }
            }
        }

}





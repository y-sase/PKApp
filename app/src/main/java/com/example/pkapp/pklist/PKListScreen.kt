package com.example.pkapp.pklist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pkapp.SearchBar.SimpleSearchBar
import com.example.pkapp.SearchBar.TypeFilterBar
import com.example.pkapp.pklist.components.PKThumbnail
import com.example.pkapp.viewmodel.PKViewModel
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.rememberLazyListState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PKListScreen(
    viewModel: PKViewModel, navController: NavController, onClick: () -> Unit
) {
    var visibleCount by remember {
        mutableStateOf(100)
    }
    val displayList =
        if (viewModel.query.isNotBlank()) {
        viewModel.pokemonList
    } else {
        viewModel.pokemonList.take(visibleCount)
    }

    val listState = rememberLazyListState()
    //val state = viewModel.state.value
    val state by viewModel.state
    var showTypeFilter by remember {
        mutableStateOf(false)
    }
    var showFavoriteOnly by remember {
        mutableStateOf(true)
    }

/*
    LaunchedEffect(Unit) {

        viewModel.loadPokemonList(
            onSuccess = {},
            onError = { navController.navigate("error_screen") })

    }

 */
    LaunchedEffect(
        listState.firstVisibleItemIndex,
        viewModel.pokemonList.size
    ) {
        val totalItems = displayList.size
        val lastVisible =
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

        if (
            lastVisible >= totalItems - 10 &&
            visibleCount < viewModel.pokemonList.size
        ) {
            visibleCount += 100
        }
    }
    LaunchedEffect(Unit) {
        Log.d(
            "CHECK_LIST",
            "size=${viewModel.pokemonList.size}"
        )
        if (viewModel.pokemonList.isEmpty()) {
            viewModel.loadPokemonList(
                onSuccess = {},
                onError = {}
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loadTypesList()
    }
/*

    LaunchedEffect(Unit) {
        viewModel.loadPokemonByTypes(
            onSuccess = {},
            onError = { navController.navigate("error_screen") })
    }

 */


    Scaffold(
        containerColor = Color.LightGray, topBar = {
            Column(
                modifier = Modifier.statusBarsPadding()//上開ける
            ) {

                Row {


                    Button(
                        enabled = viewModel.allPokemonList.isNotEmpty(),
                        onClick = {


                            showTypeFilter = !showTypeFilter
                        },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .padding(vertical = 16.dp)
                            .height(52.dp)
                            .width(90.dp)
                        //.border(1.dp, Color(0xFF90A4AE))

                    ) {
                        Text(
                            text = "タイプ",
                            color = Color(0xFF546E7A),
                        )
                    }


                    SimpleSearchBar(
                        searchText = viewModel.query,


                        onSearchPKChanged = {
                            viewModel.query = it
                        }, onDone = {
                            if (viewModel.allPokemonList.isEmpty()) {
                                viewModel.errorMessage =
                                    "データ読込中です"
                                return@SimpleSearchBar
                            }

                            val id =
                                viewModel.query.toIntOrNull()//.toIntOrNull():文字列を Int に変換する。変換できなかったら null を返す
                            val name = viewModel.query

                            if (viewModel.query == "") {
                                viewModel.pokemonList =
                                    viewModel.allPokemonList

                                viewModel.errorMessage = ""
                            } else {

                                viewModel.searchPokemon(
                                    id = id,
                                    name = name,
                                    onSuccess = {},
                                    onError = {})
                            }

                            /*

                            if (id == null) {
                                if (name == "") {
                                    viewModel.errorMessage =
                                        "エラー：数字もしくは文字を入力してください"
                                } else {
                                    viewModel.errorMessage = ""
                                    viewModel.searchPokemon(
                                        onSuccess = {},
                                        onError = { navController.navigate("error_screen") }

                                    )
                                }
                            } else {
                                viewModel.errorMessage = ""
                                viewModel.searchPokemon(
                                    onSuccess = {},
                                    onError = { navController.navigate("error_screen") })

                            }


                             */
                        }

                    )


                }
                var count = viewModel.pokemonList.size
                Row {
                    Text(

                        text = "総件数：${count}匹",
                        modifier = Modifier
                            .padding(vertical = 1.dp)
                            .padding(horizontal = 16.dp),
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .height(35.dp)
                            .width(130.dp)
                            .clip(RoundedCornerShape(10.dp)), onClick = {
                            showFavoriteOnly = !showFavoriteOnly
                            if (showFavoriteOnly) {
                                viewModel.loadPokemonList(onSuccess = {}, onError = {})

                            } else {
                                viewModel.favoritePokemon(onSuccess = {}, onError = {})

                            }


                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = if (showFavoriteOnly) {
                                Color.White
                            } else {
                                Color(0xFFE57373)
                            }
                        )
                    ) {
                        Text(
                            text = "お気に入りのみ表示",
                            color = Color.Black,
                            fontSize = 11.sp,
                            //fontSize = 20.sp,
                            //lineHeight = 43.sp
                        )
                    }
                }
                Text(
                    text = viewModel.errorMessage,
                    color = Color.Red,
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                        .padding(horizontal = 16.dp),
                    fontSize = 15.sp
                )





                if (showTypeFilter) {

                    TypeFilterBar(
                        viewModel = viewModel, navController = navController, onClose = {
                            showTypeFilter = false
                        })

                }
            }

        }) { paddingValues ->
        Column {

            Text(
                text = "size=${viewModel.pokemonList.size}"
            )
            LazyColumn(
                state = listState,
                modifier = Modifier.padding(paddingValues)
            ) {

                if (state.isLoading) {
                    items(10) {//(viewModel.pokemonList) { pokemon -> //pokemonListからポケモンを1匹ずつ取り出して、pokemonという名前で使う,for文みたいな

                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(Color.White)
                                .heightIn(100.dp)
                                .fillMaxWidth()/*
                            .border(
                                width = 3.dp, Color(0xFF90A4AE), shape = RoundedCornerShape(20.dp)
                            )

                             */


                        ) {
                            //ローディング
                            CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
                        }

                    }

                } else {
                    Log.d(
                        "AFTER_LOAD",
                        "size=${viewModel.pokemonList.size}"
                    )

                    items(
                        items = displayList,
                        key = { it.id }
                    ) { pokemon ->

                        Log.d(
                            "ITEMS",
                            "${pokemon.name} jp=${pokemon.listJpName}"
                        )
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.White)
                                .heightIn(100.dp)/*
                            .border(
                                width = 3.dp, Color(0xFF90A4AE), shape = RoundedCornerShape(20.dp)
                            )

                             */
                        ) {
                            PKThumbnail(

                                id = pokemon.id, //name = pokemon.name,
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





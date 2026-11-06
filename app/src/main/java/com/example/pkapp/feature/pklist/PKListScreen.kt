package com.example.pkapp.feature.pklist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pkapp.SearchBar.SimpleSearchBar
import com.example.pkapp.SearchBar.TypeFilterBar
import com.example.pkapp.feature.pklist.components.PKThumbnail
import com.example.pkapp.pklist.components.EmptyScreen
import com.example.pkapp.viewmodel.PKViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PKListScreen(
    viewModel: PKViewModel, navController: NavController, onClick: () -> Unit
) {
    var visibleCount by remember {
        mutableStateOf(20)
    }
    var selectedRange by remember {
        //mutableStateOf<IntRange?>(null)
        mutableStateOf(1..20)
    }
    var unlockedRangeEnd by remember {
        mutableStateOf(20)
    }
    var showFavoriteOnly by remember {
        mutableStateOf(false)
    }
    var showTypeFilter by remember {
        mutableStateOf(false)
    }
    val displayList = when {
        showFavoriteOnly -> {
            viewModel.pokemonList
        }

        viewModel.query.isNotBlank() -> {
            viewModel.pokemonList
        }

        viewModel.typeIds.isNotEmpty() -> {

            viewModel.pokemonList

        }

        selectedRange != null -> {
            viewModel.pokemonList.filter {
                it.id in selectedRange!!
            }
        }


        else -> {
            viewModel.pokemonList
        }
    }/*
    LaunchedEffect(selectedRange) {
        viewModel.loadTypesForRange(displayList)

    }

     */
    LaunchedEffect(
        selectedRange, viewModel.pokemonList.size
    ) {
        if (displayList.isNotEmpty()) {
            viewModel.loadTypesForRange(displayList)
        }
    }
    // val displayList = viewModel.pokemonList.take(visibleCount)
    val listState = rememberLazyListState()
    //val state = viewModel.state.value
    val state by viewModel.state


    var showLoadingBox by remember {
        mutableStateOf(false)
    }
    val maxId = viewModel.allPokemonList.size
    val ranges = (1..1351 step 20).map { start ->
        start..minOf(start + 19, 1351)
    }
    val count = viewModel.pokemonList.size
    val percent = (count * 100) / 1351



    LaunchedEffect(
        listState.firstVisibleItemIndex, viewModel.pokemonList.size
    ) {
        val totalItems = displayList.size
        val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

        if (lastVisible >= totalItems - 10 && visibleCount < viewModel.pokemonList.size) {
            visibleCount += 20
        }
    }
    LaunchedEffect(Unit) {

        if (viewModel.pokemonList.isEmpty()) {
            viewModel.loadPokemonList(onSuccess = {}, onError = {})
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loadTypesList()

    }
    LaunchedEffect(showLoadingBox) {
        if (showLoadingBox) {
            delay(2000)
            showLoadingBox = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.LightGray, Color.White
                    )
                )
            )
    ) {
        Scaffold(
            containerColor = Color(0xFFF5F5F5), topBar = {
                Column(
                    modifier = Modifier.statusBarsPadding()//上開ける
                ) {

                    Row {

                        Text(
                            text = "Pokemon Apk",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF37474F),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Icon(

                            imageVector = Icons.Default.CatchingPokemon,
                            contentDescription = null,
                            tint = Color.Red.copy(alpha = 0.7f),
                            modifier = Modifier
                                .requiredSize(40.dp)
                                .offset(x = (-80).dp)


                        )
                    }
                    Row(
                        modifier = Modifier.offset(x = (14).dp)
                    ) {


                        Button(

                            //enabled = viewModel.allPokemonList.isNotEmpty(),
                            onClick = {

                                if (viewModel.allPokemonList.isNotEmpty()) {
                                    showTypeFilter = !showTypeFilter
                                } else {
                                    showLoadingBox = true
                                }
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
                            //if (viewModel.allPokemonList.isNotEmpty()) {
                            Text(
                                text = "タイプ",
                                color = Color(0xFF546E7A),
                            )
                            // } else { showLoadingBox = true
                            //CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 1.5.dp)//(modifier = Modifier.align(androidx.compose.ui.Alignment.Center)
                            //}
                        }


                        SimpleSearchBar(
                            searchText = viewModel.query,


                            onSearchPKChanged = {
                                viewModel.query = it
                            }, onDone = {
                                if (viewModel.allPokemonList.isEmpty()) {
                                    showLoadingBox = true
                                    //viewModel.errorMessage = "データ読込中です"
                                    return@SimpleSearchBar
                                }
                                viewModel.errorMessage = ""


                                val id =
                                    viewModel.query.toIntOrNull()//.toIntOrNull():文字列を Int に変換する。変換できなかったら null を返す
                                val name = viewModel.query

                                if (viewModel.query == "") {
                                    viewModel.pokemonList = viewModel.allPokemonList

                                    viewModel.errorMessage = ""
                                } else {

                                    viewModel.searchPokemon(
                                        id = id,
                                        name = name,
                                        onSuccess = {},
                                        onError = {})
                                }


                            }

                        )


                    }
                    var count = viewModel.pokemonList.size
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {

                        if (!viewModel.allPokemonList.isNotEmpty()) {


                            LinearProgressIndicator(
                                progress = { count.toFloat() / viewModel.totalCount.toFloat() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Text(
                                text = "読込中：$count / ${viewModel.totalCount}匹 ($percent%)",
                                modifier = Modifier
                                    .padding(vertical = 1.dp)
                                    .padding(horizontal = 16.dp),
                            )

                        }


                    }
                    if (!showFavoriteOnly && !showTypeFilter && viewModel.typeIds.isEmpty()) {
                        LazyRow {
                            items(ranges) { range ->
                                Spacer(modifier = Modifier.width(10.dp))

                                Button(
                                    enabled = range.last <= viewModel.pokemonList.size,
                                    onClick = {
                                        selectedRange = range


                                    },
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(95.dp)
                                        //.border(2.dp, Color.Black,shape = RoundedCornerShape(3.dp))
                                        .clip(RoundedCornerShape(3.dp)),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = // if (/*TODO*/) Color.LightGray else
                                            Color.White
                                    ),

                                    ) {
                                    Text(
                                        text = "${range.first}~",//${range.last}",
                                        color = Color(0xFF37474F),
                                        fontSize = 15.sp,
                                        //fontWeight = FontWeight.Bold,
                                    )
                                }


                            }
                        }
                    }

                    //Spacer(modifier = Modifier.weight(1f))


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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                if (!state.isLoading && viewModel.allPokemonList.isNotEmpty() && displayList.isEmpty()) {
                    EmptyScreen()
                } else {

                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(
                            bottom = 120.dp
                        )
                        // contentPadding = PaddingValues(top = 250.dp, bottom = 120.dp)
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
                                        .fillMaxWidth()
                                ) {
                                    //ローディング
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

                                }

                            }

                        } else {


                            items(
                                items = displayList, key = { it.id }) { pokemon ->


                                /*
                            .border(
                                width = 3.dp, Color(0xFF90A4AE), shape = RoundedCornerShape(20.dp)
                            )

                             */




                                PKThumbnail(

                                    id = pokemon.id, //name = pokemon.name,
                                    pokemonImageInList = pokemon,
                                    name = pokemon.listJpName,
                                    form = pokemon.formName,
                                    type = pokemon.typeName,
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
        if (!showTypeFilter) {
            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFFF5F5F5))
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                        .align(Alignment.Center),

                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Spacer(modifier = Modifier.weight(1f))
                    Box(

                        modifier = Modifier.weight(1f),


                        )



                    Box(

                        modifier = Modifier.weight(1f),

                        contentAlignment = Alignment.Center

                    ) {
                        IconButton(
                            onClick = {


                                if (viewModel.allPokemonList.isEmpty()) {
                                    showLoadingBox = true
                                    return@IconButton
                                }
                                showFavoriteOnly = false
                                viewModel.isFavoriteMode = false
                                //viewModel.query = ""
                                //viewModel.resettype()
                                //viewModel.pokemonList = viewModel.allPokemonList
                                viewModel.pokemonList = viewModel.allPokemonList
                                if (viewModel.typeIds.isNotEmpty()) {
                                    viewModel.loadPokemonByTypes(onSuccess = {
                                        if (viewModel.query.isNotBlank()) {
                                            viewModel.searchPokemon(

                                                id = viewModel.query.toIntOrNull(),

                                                name = viewModel.query,
                                                onSuccess = {},
                                                onError = {})
                                        }

                                    }, onError = {}

                                    )

                                } else if (viewModel.query.isNotBlank()) {
                                    viewModel.searchPokemon(
                                        id = viewModel.query.toIntOrNull(),
                                        name = viewModel.query,
                                        onSuccess = {},
                                        onError = {})
                                }
                            }) {
                            Icon(

                                modifier = Modifier.size(40.dp),
                                imageVector = Icons.Default.List,
                                contentDescription = "List",
                                tint = Color.Gray

                            )
                        }
                    }
                    Box(

                        modifier = Modifier.weight(1f),


                        )
                    Box(

                        modifier = Modifier.weight(1f),

                        contentAlignment = Alignment.Center

                    ) {
                        IconButton(
                            //enabled = viewModel.allPokemonList.isNotEmpty(),

                            onClick = {


                                if (viewModel.allPokemonList.isNotEmpty()) {
                                    showFavoriteOnly = true
                                    viewModel.isFavoriteMode = true
                                    viewModel.favoritePokemon(onSuccess = {}, onError = {})
                                } else {
                                    showLoadingBox = true
                                }


                            },
                        ) {
                            Icon(

                                modifier = Modifier.size(40.dp),
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = Color.Gray

                            )


                        }
                    }
                    Box(modifier = Modifier.weight(1f))
                    //    Spacer(modifier = Modifier.weight(1f))


                }
            }
        }
        AnimatedVisibility(
            visible = showLoadingBox, modifier = Modifier.align(Alignment.Center), exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "データ読込中です\n読込終了後再度お試しください", color = Color.Black, fontSize = 15.sp
                )
            }
        }
    }

}







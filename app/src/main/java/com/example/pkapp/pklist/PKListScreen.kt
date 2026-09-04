package com.example.pkapp.pklist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pkapp.api.PokemonDetailResponse
import com.example.pkapp.pklist.components.PKThumbnail
import com.example.pkapp.ui.theme.ScreenRoute
import com.example.pkapp.viewmodel.PKViewModel


@Composable
fun PKListScreen(
    viewModel: PKViewModel,
    navController: NavController,
    onClick: () -> Unit
) {

    LaunchedEffect(Unit) {

        viewModel.loadPokemonList(onSuccess = {}, onError = {})

    }


    Scaffold(
        containerColor = Color.LightGray, topBar = {
            /*{
    SearchBar(

        searchText = viewModel.query,
        onSearchPKChanged = { viewModel.query = it },
        onDone = { viewModel.searchPK() },


    )*/

        }) { paddingValues ->
        Column {

            /*
    when {
        state.isLoading -> {
            //ローディング
            CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
        }

        !state.error.isNullOrBlank() -> {
            Text(
                text = state.error,
                modifier = Modifier.align(androidx.compose.ui.Alignment.Center),
                color = MaterialTheme.colorScheme.error,
            )

        }
        else -> {
            PKThumbnail()
        }*/
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
            ) {
                items(viewModel.pokemonList) { pokemon -> //pokemonListからポケモンを1匹ずつ取り出して、pokemonという名前で使う,for文みたいな

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White)
                            .heightIn(100.dp)
                            .border(
                                width = 3.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)
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
                            }
                            )
                    }

                }


                /*
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(state.photos) { photo ->
            PKThumbnail(
                photo = photo,
                /*
                onClick = {//画面遷移
                    navController.navigate(ScreenRoute.PkDetailScreen.route + "/${photo.photoId}")
                }*/
                )


        }
    }
    */

            }
        }
    }
}


package com.example.pkapp.pklist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pkapp.pklist.components.PKThumbnail


@Composable
fun PKListScreen(

) {

    Scaffold(
        containerColor = Color.LightGray,
        topBar =
            {
                /*{
        SearchBar(

            searchText = viewModel.query,
            onSearchPKChanged = { viewModel.query = it },
            onDone = { viewModel.searchPK() },


        )*/

            }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
            //navController: NavController,//画面遷移
        ) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .heightIn(100.dp)
                    .border(
                        width = 3.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
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
                PKThumbnail()
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

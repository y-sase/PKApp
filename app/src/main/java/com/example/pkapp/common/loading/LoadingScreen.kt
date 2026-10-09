package com.example.pkapp.loading

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.pkapp.ui.theme.ScreenRoute
import com.example.pkapp.viewmodel.PKViewModel

@Composable
fun LoadingScreen(
    viewModel: PKViewModel,
    navController: NavController,
    mode: LoadingMode
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        CircularProgressIndicator()//ぐるぐる回るローディングマーク

        Text(
            text = "Loading..."
        )

        LaunchedEffect(Unit) {//画面が表示された瞬間に中の処理を1回だけ実行

            when (mode) {
                LoadingMode.DETAIL -> {

                    viewModel.loadPokemonDetail(
                        id = viewModel.PKId,
                        onSuccess = {

                        navController.navigate(
                            "pkdetail_screen" + "/{pkId}"
                        )
                    }, onError = {

                        navController.navigate(

                            "error_screen"
                        )
                    }
                    )
                }

                LoadingMode.LIST -> {
                    viewModel.loadPokemonList(
                        onSuccess = {


                        navController.navigate(
                            "pklist_screen"
                        )
                    }, onError = {

                        navController.navigate(
                            "error_screen"

                        )

                    }
                    )
                }
            }
        }
    }
}








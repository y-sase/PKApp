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
                    Log.d("TEST", "DETAIL SUCCESS")

                    viewModel.loadPokemonDetail(
                        id = viewModel.PKId,
                        onSuccess = {
                            Log.d("TEST", "SUCCESS")
                        navController.navigate(
                            "pkdetail_screen" + "/{pkId}"
                        )
                    }, onError = {
                            Log.d("TEST", "DETAIL ERROR")
                        navController.navigate(

                            "error_screen"
                        )
                    }
                    )
                }

                LoadingMode.LIST -> {
                    viewModel.loadPokemonList(
                        onSuccess = {
                            Log.d("TEST", "LIST SUCCESS")

                        navController.navigate(
                            "pklist_screen"
                        )
                    }, onError = {
                            Log.d("TEST", "LIST ERROR")
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
/*
            if (viewModel.query.isBlank()) {

                // 一覧画面 → Loading画面 → 詳細画面

                viewModel.loadPokemonList(
                    onSuccess = {



                        val intent = Intent(//Intentオブジェクト作成
                            context,//今いるActivityの情報、「どこから起動するのか」を指定
                            PokemonDetailActivity::class.java
                        )



                        intent.putExtra(//取得したnameをIntentに入れる
                            "name",
                            viewModel.PKName
                        )

                        intent.putExtra(//取得したアドバイスIDをIntentに入れる
                            "id",
                            viewModel.PKId
                        )

                        intent.putExtra(//取得したアドバイスIDをIntentに入れる
                            "height",
                            viewModel.PKHeight
                        )

                        intent.putExtra(//取得したアドバイスIDをIntentに入れる
                            "weight",
                            viewModel.PKWeight
                        )

                        intent.putExtra(//取得したアドバイスIDをIntentに入れる
                            "types",
                            viewModel.PKTypes
                        )



                        context.startActivity(intent)//実際に画面遷移



                        navController.navigate(ScreenRoute.PKDetailScreen.route)



             */







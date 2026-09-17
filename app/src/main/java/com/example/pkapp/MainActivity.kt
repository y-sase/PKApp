package com.example.pkapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pkapp.Error.ErrorScreen
import com.example.pkapp.api.RetrofitInstance
import com.example.pkapp.loading.LoadingMode
import com.example.pkapp.loading.LoadingScreen
import com.example.pkapp.pkdetail.PKDetailScreen
import com.example.pkapp.pklist.PKListScreen
import com.example.pkapp.repository.PKRepositoryImpl
import com.example.pkapp.ui.theme.PKAppTheme
import com.example.pkapp.ui.theme.ScreenRoute
import com.example.pkapp.viewmodel.PKViewModel



class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PKAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    val api = RetrofitInstance.providePKApi()
                    val repository = PKRepositoryImpl(api)
                    val viewModel = PKViewModel(repository)


                    val navController =
                        rememberNavController()//navControllerをインスタンスで保持できる。画面遷移を管理するオブジェクト


                    NavHost(
                        //画面遷移のルールを定義
                        navController = navController,
                        startDestination = ScreenRoute.PKListScreen.route,

                        ) {

                        //ポケモン一覧画面
                        composable("pklist_screen") {
                            PKListScreen(
                                viewModel = viewModel, navController = navController, onClick = {})
                        }

                        //ポケモン詳細画面
                        composable("pkdetail_screen" + "/{pkId}") {
                            PKDetailScreen(
                                viewModel = viewModel, navController = navController, onClick = {})
                        }


                        //Loading画面
                        composable("loading_detail/{id}") {backStackEntry -> //backStackEntry:この画面に渡された情報が入っている箱
                            val id =backStackEntry.arguments?.getString("id")?.toInt() ?: 0//画面遷移で渡された情報が入っている箱.箱の中の「引数一覧」?（nullでもエラーにしない）引数一覧の中からidを取り出す.文字列(String)をInt型に変換する
                            LoadingScreen(
                                viewModel = viewModel,
                                navController = navController,
                                mode = LoadingMode.DETAIL,
                                id=id
                            )
                        }
                        composable("loading_list") {
                            LoadingScreen(
                                viewModel = viewModel,
                                navController = navController,
                                mode = LoadingMode.LIST,
                                id=0
                            )
                        }

                        //Error画面
                        composable("errorlist_screen") {
                            ErrorScreen(
                                viewModel = viewModel,
                                navController = navController,
                                from = "list",
                                onClick = {},

                            )
                        }

                        composable("errordetail_screen") {
                            ErrorScreen(
                                viewModel = viewModel,
                                navController = navController,
                                from = "detail",
                                onClick = {}
                            )
                        }
                    }


                }
            }
        }
    }
}



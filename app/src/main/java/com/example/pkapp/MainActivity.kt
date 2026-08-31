package com.example.pkapp

import android.R.attr.id
import android.R.attr.name
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pkapp.Error.ErrorScreen
import com.example.pkapp.pklist.PKListScreen
import com.example.pkapp.ui.theme.PKAppTheme
import com.example.pkapp.viewmodel.PKViewModel

import com.example.pkapp.repository.PKRepositoryImpl
import com.example.pkapp.api.RetrofitInstance
import com.example.pkapp.loading.LoadingScreen
import com.example.pkapp.pkdetail.PKDetailScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PKAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val api = RetrofitInstance.providePKApi()
                    val repository = PKRepositoryImpl(api)
                    val viewModel = PKViewModel(repository)
/*
                    PKListScreen(
                        viewModel = viewModel
                    )

                    PKDetailScreen(
                        viewModel = viewModel,

                    )



                    LoadingScreen()

 */
                    ErrorScreen()

                }
                /*
                val navController = rememberNavController()//画面遷移を管理するオブジェクト


                NavHost(
                    //画面遷移のルールを定義
                    navController = navController,
                    startDestination = ScreenRoute.PKListScreen.route,

                    ) {
                    //ポケモン一覧画面
                    composable(route = ScreenRoute.PKListScreen.route) {
                        PKListScreen(
                            navController = navController,
                            viewModel = pkViewModel
                        )
                    }

                    /*
                    //Loading画面
                    composable(ScreenRoute.LoadingScreen.route) {
                        LoadingScreen(
                            viewModel = pkViewModel,
                            navController = navController
                        )
                    }

                     */*/

                }
            }
        }
    }



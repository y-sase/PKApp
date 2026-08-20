package com.example.pkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pkapp.pklist.PKListScreen
import com.example.pkapp.ui.theme.PKAppTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.pkapp.api.PKViewModel

import com.example.pkapp.api.PKRepositoryImpl
import com.example.pkapp.api.RetrofitInstance
import retrofit2.Retrofit


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

                    PKListScreen(
                        viewModel = viewModel
                    )

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



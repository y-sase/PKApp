package com.example.pkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pkapp.ui.theme.PKAppTheme
import com.example.pkapp.viewmodel.PKViewModel

import com.example.pkapp.data.api.repository.PKRepositoryImpl
import com.example.pkapp.data.api.RetrofitInstance
import com.example.pkapp.feature.pklist.pkdetail.PKDetailScreen

import com.example.pkapp.feature.pklist.PKListScreen

//import com.example.pkapp.pkdetail.PKDetailScreen


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

                    */
                    //詳細画面部分
                    PKDetailScreen(
                        viewModel = viewModel,

                    )



                }


                }
            }
        }
    }



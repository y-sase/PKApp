/*
package com.example.pkapp.use_case

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pkapp.pkdetail.PKDetailScreen
import com.example.pkapp.viewmodel.PKViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pkapp.api.RetrofitInstance
import com.example.pkapp.repository.PKRepositoryImpl

class PokemonDetailActivity : ComponentActivity(

) {

    override fun onCreate(savedInstanceState: Bundle?) {//Activityが起動した瞬間に呼ばれる関数
        super.onCreate(savedInstanceState)//親クラス(ComponentActivity)の初期化処理、Activityではほぼ必ず書く

        //val name = intent.getStringExtra("name") ?: ""//Intentからnameというデータを取り出している,もし取り出せなかったら空文字""
        val id = intent.getIntExtra("id", 0)//Intentからidというデータを取り出している,データが見つからなかった場合の初期値0

        /*
        val api = RetrofitInstance.providePKApi()
        val repository = PKRepositoryImpl(api)
        val viewModel = PKViewModel(repository)
        val navController = rememberNavController()//画面遷移を管理するオブジェクト
         */

        setContent {
            PKDetailScreen(
                viewModel = viewModel,
               navController = navController,

                onClick = {},



            )

        }
    }
}


 */
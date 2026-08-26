package com.example.pkapp.viewmodel

import android.R.attr.id
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkapp.model.PokemonListItem
import com.example.pkapp.model.Sprites
import com.example.pkapp.model.TypeSlot
import com.example.pkapp.repository.PKRepository
import kotlinx.coroutines.launch

class PKViewModel(
    private val repository: PKRepository,
) : ViewModel() {
    var PKId by mutableStateOf(0)
    var PKName by mutableStateOf("")
    var PKHeight by mutableStateOf(0)
    var PKWeight by mutableStateOf(0)
    var PKSprites by mutableStateOf(Sprites(""))
    var PKTypes by mutableStateOf("")
    var query by mutableStateOf("")
    var errorMessage by mutableStateOf("")


    var pokemonList by mutableStateOf<List<PokemonListItem>>(//<List<PokemonDetailResponse>>はPokemonDetailResponseをたくさん入れられるリスト型
        emptyList()//空っぽのリストを作る関数
    )


    /*
    //詳細画面の際に使用（一匹ずつの情報）
    fun loadPKById(
        id: Int,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                PKId = 0

                val response = repository.getPokemon(id)

                PKId = response.id// ViewModelに保存
                PKName = response.name
                PKHeight = response.height
                PKWeight = response.weight
                PKSprites = response.sprites
                PKTypes = response.types.joinToString(", ") {//joinToString() は,リストの要素をつなげて、1つの文字列(String)にする関数
                    it.type.name
                }


                onSuccess()

            } catch (e: Exception) {
                errorMessage = "エラー: ${e.message}"
            }
        }
    }

     */
    fun loadPokemonList() {
        errorMessage = "開始"
        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。
            try {//エラーが起きるかもしれない処理を開始。

                //isLoading = true
                /*
                                val list = mutableListOf<PokemonDetailResponse>()//空のリストを作る。
                                for (limit) {
                                    list.add(
                                        repository.getPokemonList()//Repository経由でAPIからポケモンを取得。
                                    )
                                }
                                pokemonList = list

                 */

                val responselist = repository.getPokemonList()
                pokemonList = responselist.results
                errorMessage = "成功 ${pokemonList.size}"
            } catch (e: Exception) {
                //errorMessage = "エラー: ${e.message}"
                errorMessage = e.toString()
            }




        }

    }
    fun loadPokemonDetail(id: Int) {
        errorMessage = "開始"
        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。
            try {//エラーが起きるかもしれない処理を開始。

                //isLoading = true
                /*
                                val list = mutableListOf<PokemonDetailResponse>()//空のリストを作る。
                                for (limit) {
                                    list.add(
                                        repository.getPokemonList()//Repository経由でAPIからポケモンを取得。
                                    )
                                }
                                pokemonList = list

                 */

                val responsedetail = repository.getPokemonDetail(id)
                PKId = responsedetail.id
                PKName = responsedetail.name
                PKSprites = responsedetail.sprites
                PKHeight = responsedetail.height
                PKWeight = responsedetail.weight
                PKTypes = responsedetail.types.joinToString(", ") {
                    it.type.name
                }
                errorMessage = "成功 ${pokemonList.size}"
            } catch (e: Exception) {
                //errorMessage = "エラー: ${e.message}"
                errorMessage = e.toString()
            }
        }
    }
}
package com.example.pkapp.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkapp.model.getJapaneseName
import com.example.pkapp.model.getTypeList
import com.example.pkapp.model.PokemonListItem
import com.example.pkapp.model.Sprites

import com.example.pkapp.data.api.repository.PKRepository
import kotlinx.coroutines.launch

class PKViewModel(
    private val repository: PKRepository,
) : ViewModel() {
    var pokemonId by mutableStateOf(0)
    var PKName by mutableStateOf("")
    var PKHeight by mutableStateOf(0)
    var PKWeight by mutableStateOf(0)
    var PKSprites by mutableStateOf(Sprites(""))
    var PKTypes by mutableStateOf("")
    var query by mutableStateOf("")
    var errorMessage by mutableStateOf("")

    var favoriteIds by mutableStateOf<List<Int>>(emptyList())


    var pokemonList by mutableStateOf<List<PokemonListItem>>(//<List<PokemonDetailResponse>>はPokemonDetailResponseをたくさん入れられるリスト型
        emptyList()//空っぽのリストを作る関数
    )

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

    //詳細画面部分
    fun loadPokemonDetail(id: Int) {
        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。
            try {//エラーが起きるかもしれない処理を開始。

                val responsedetail = repository.getPokemonDetail(id)
                val responsejpname = repository.getPokemonJpName(responsedetail.name)

                pokemonId = responsedetail.id
                PKSprites = responsedetail.sprites
                PKHeight = responsedetail.height
                PKWeight = responsedetail.weight
                PKName = getJapaneseName(responsejpname)
                PKTypes = getTypeList(repository,id).joinToString(" / ")


            } catch (e: Exception) {
                errorMessage = e.toString()
            }

        }
    }

    fun toggleFavorite(id: Int) {
        favoriteIds =
            if (id in favoriteIds) {
                favoriteIds - id
            } else {
                favoriteIds + id
            }
    }

}
package com.example.pkapp.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkapp.data.api.repository.PKRepository
import com.example.pkapp.model.PokemonListItem
import com.example.pkapp.model.Sprites
import com.example.pkapp.model.getJapaneseName
import com.example.pkapp.model.getTypeList
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


                val responselist = repository.getPokemonList()
                pokemonList = responselist.results
                errorMessage = "成功 ${pokemonList.size}"
            } catch (e: Exception) {
                //errorMessage = "エラー: ${e.message}"
                errorMessage = e.toString()
            }


        }

    }

    /*
    詳細画面部分


    fun loadPokemonDetail(id: Int) {
        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。
            try {//エラーが起きるかもしれない処理を開始。

                val responsedetail = repository.getPokemonDetail(id)
                val responsejpname = repository.getPokemonJpName(responsedetail.name)
                val typeNames = responsedetail.types.map { typeInfo ->//typeInfoは今処理中の1件
                    val typeId = typeInfo
                        .type.url
                        .trimEnd('/')
                        .substringAfterLast('/')//最後の / より後ろだけ取得
                        .toInt()//文字列を数値に変換 String->Int

                    val responsejptype = repository.getPokemonJpType(typeId)
                    ChangeLanguageType(
                        responsedetail,
                        responsejptype
                    ).first()
                }
                //println(responsejpname.names)
                PKId = responsedetail.id
                //PKName = responsejpname.name

                pokemonId = responsedetail.id
                PKSprites = responsedetail.sprites
                PKHeight = responsedetail.height
                PKWeight = responsedetail.weight
                PKName = getJapaneseName(responsejpname)
                PKTypes = getTypeList(repository, responsedetail).joinToString(" / ")


            } catch (e: Exception) {
                errorMessage = e.toString()
            }

        }
    }

     */

    fun toggleFavorite(id: Int) {
        favoriteIds = if (id in favoriteIds) {
            favoriteIds - id
        } else {
            favoriteIds + id
        }
    }

}
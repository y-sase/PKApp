package com.example.pkapp.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkapp.SearchBar.TypeListItem
import com.example.pkapp.api.TypeListResponse
import com.example.pkapp.common.NetworkResponse
import com.example.pkapp.model.ChangeLanguageName
import com.example.pkapp.model.ChangeLanguageType
import com.example.pkapp.model.PokemonListItem
import com.example.pkapp.model.Sprites
import com.example.pkapp.pklist.PKListState
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

    var favoriteIds by mutableStateOf<List<Int>>(emptyList())
    var typeIds by mutableStateOf<List<Int>>(emptyList())
    var typeList by mutableStateOf<List<TypeListItem>>(emptyList())



    var pokemonList by mutableStateOf<List<PokemonListItem>>(//<List<PokemonDetailResponse>>はPokemonDetailResponseをたくさん入れられるリスト型
        emptyList()//空っぽのリストを作る関数
    )

    private val _state = mutableStateOf(PKListState())//mutableStateOfとvalueはセットで値が随時変わるときに使う
    val state: State<PKListState> = _state
    var isLoading by mutableStateOf(false)


    fun loadPokemonList(
        onSuccess: () -> Unit, onError: () -> Unit
    ) {
        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。

            _state.value = PKListState(isLoading = true)


            when (val result = repository.getPokemonList()) {
                is NetworkResponse.Loading -> {
                    isLoading = true
                    _state.value = PKListState(isLoading = true)
                }


                is NetworkResponse.Success -> {
                    pokemonList = result.data?.results ?: emptyList()
                    isLoading = false
                    _state.value = PKListState(
                        isLoading = false
                    )
                    onSuccess()
                }

                is NetworkResponse.Failure -> {
                    isLoading = false
                    _state.value = PKListState(
                        error = result.error, isLoading = false
                    )
                    onError()

                }

            }

        }
    }


    fun loadPokemonDetail(
        id: Int, onSuccess: () -> Unit, onError: () -> Unit
    ) {
        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。
            try {//エラーが起きるかもしれない処理を開始。


                val responsedetail = repository.getPokemonDetail(id)
                val responsejpname = repository.getPokemonJpName(responsedetail.name)
                val typeNames = responsedetail.types.map { typeInfo ->//typeInfoは今処理中の1件
                    val typeId =
                        typeInfo.type.url.trimEnd('/').substringAfterLast('/')//最後の / より後ろだけ取得
                            .toInt()//文字列を数値に変換 String->Int

                    val responsejptype = repository.getPokemonJpType(typeId)
                    ChangeLanguageType(
                        responsedetail, responsejptype
                    ).first()
                }
                PKId = responsedetail.id
                //PKName = responsejpname.name
                PKSprites = responsedetail.sprites
                PKHeight = responsedetail.height
                PKWeight = responsedetail.weight
                PKName = ChangeLanguageName(responsedetail, responsejpname)
                PKTypes = typeNames.joinToString(" / ")

                onSuccess()//取得成功後に画面遷移する

            } catch (e: Exception) {
                onError()
            }
        }
    }

    fun toggleFavorite(id: Int) {
        favoriteIds = if (id in favoriteIds) {
            favoriteIds - id
        } else {
            favoriteIds + id
        }
    }

    //APIで取得したタイプ一覧リスト
    fun loadTypeList(){
        viewModelScope.launch {
            val responsetypelist = repository.getTypeList()
            typeList = responsetypelist.results

            println(typeList)
            println(typeList.size)
        }
    }


    //絞り込んだタイプのIDを保持するリスト
    fun toggletype(id: Int){
        typeIds = if (id in typeIds) {
            typeIds - id
        } else {
            typeIds + id
        }
    }

    //タイプリセット
    fun resettype(){
        typeIds = emptyList()
    }



}
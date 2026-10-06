package com.example.pkapp.viewmodel


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkapp.SearchBar.TypeListItem
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


    var searchId by mutableStateOf(0)
    var pokemonList by mutableStateOf<List<PokemonListItem>>(//<List<PokemonDetailResponse>>はPokemonDetailResponseをたくさん入れられるリスト型
        emptyList()//空っぽのリストを作る関数
    )
    var allPokemonList by mutableStateOf<List<PokemonListItem>>(
        emptyList()
    )

    private val _state = mutableStateOf(PKListState())//mutableStateOfとvalueはセットで値が随時変わるときに使う
    val state: State<PKListState> = _state
    var isLoading by mutableStateOf(false)
    // var count by mutableStateOf(0)


    fun loadPokemonList(
        onSuccess: () -> Unit, onError: () -> Unit
    ) {
        Log.d("LIST", "success start")
        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。

            _state.value = PKListState(isLoading = true)


            when (val result = repository.getPokemonList()) {
                is NetworkResponse.Loading -> {
                    isLoading = true
                    _state.value = PKListState(isLoading = true)
                }


                is NetworkResponse.Success -> {

                    //val responsedetail = repository.getPokemonDetail(id)
                    //val responsejpname = repository.getPokemonJpName(PokemonListItem.name)
                    Log.d("LIST", "success")
                    val pokemonListJpName =
                        result.data?.results?.map { pokemon ->
                            val response = repository.getPokemonJpName(pokemon.name)
                            PokemonListItem(
                                name = pokemon.name,
                                jpName = ChangeLanguageName(response),
                                url = pokemon.url
                            )
                        } ?: emptyList()
                    Log.d("LIST", "finish map")
                    allPokemonList = pokemonListJpName
                    pokemonList = pokemonListJpName
                    isLoading = false

                    _state.value = PKListState(
                        isLoading = false
                    )
                    onSuccess()
                }

                is NetworkResponse.Failure -> {
                    Log.d("LIST", "failure=${result.error}")
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
                PKName = ChangeLanguageName(responsejpname)
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
    fun loadPokemonByTypes(
        onSuccess: () -> Unit, onError: () -> Unit
    ) {
        viewModelScope.launch {
            _state.value = PKListState(isLoading = true)

            if (typeIds.isEmpty()) {
                loadPokemonList(
                    onSuccess = onSuccess, onError = onError
                )
                return@launch
            }
            var filteredList: List<PokemonListItem>? = null

            for (typeId in typeIds) {
                when (val result = repository.getPokemonListbyType(typeId)) {
                    is NetworkResponse.Loading -> {
                        isLoading = true
                        _state.value = PKListState(isLoading = true)
                    }


                    is NetworkResponse.Success -> {
                        val currentList = result.data?.pokemon?.map {//map：リストの中身を1個ずつ別の形に変換する
                            it.pokemon
                        } ?: emptyList()

                        filteredList = if (filteredList == null) {
                            currentList
                        } else {
                            filteredList?.filter {
                                it in currentList
                            }
                        }

                        isLoading = false
                        _state.value = PKListState(
                            isLoading = false
                        )

                    }

                    is NetworkResponse.Failure -> {
                        Log.d("TYPE_SEARCH", "error=${result.error}")
                        isLoading = false
                        _state.value = PKListState(
                            error = result.error, isLoading = false
                        )
                        onError()

                    }


                }
            }
            pokemonList = filteredList ?: emptyList()
            onSuccess()
        }
    }
    suspend fun getJpName(name: String): String {
        val response = repository.getPokemonJpName(name)
        return ChangeLanguageName(response)
    }


    //絞り込んだタイプのIDを保持するリスト
    fun toggletype(id: Int) {
        typeIds = if (id in typeIds) {
            typeIds - id
        } else {
            typeIds + id
        }

    }

    fun favoritePokemon(
        onSuccess: () -> Unit, onError: () -> Unit
    ) {

        pokemonList = allPokemonList.filter { pokemon ->
            //idのポケモン入れる処理
            pokemon.id in favoriteIds
        }
        onSuccess()
    }


    //タイプリセット
    fun resettype() {
        typeIds = emptyList()
    }


    //APIで取得したタイプ一覧リスト
    fun loadTypesList() {

        viewModelScope.launch {
            val responseTypeList = repository.getTypeList()
            typeList = responseTypeList.results

        }
    }

    //
    fun searchPokemon(
        id: Int?, name: String, onSuccess: () -> Unit, onError: () -> Unit
    ) {


        when {
            id != null -> {
                pokemonList = allPokemonList.filter { pokemon ->
                    //idのポケモン入れる処理
                    pokemon.id == id
                }
                onSuccess()
            }

            name != "" -> {
                pokemonList = allPokemonList.filter { pokemon ->
                    Log.d(
                        "SEARCH",
                        "${pokemon.name} / ${pokemon.jpName}"
                    )
                    //nameのポケモン入れる処理
                    pokemon.name.contains(
                        name, ignoreCase = true
                    )//contains():文字列の中に指定した文字が含まれているか調べる  ignoreCase = true:大文字小文字を無視する
                            ||

                    pokemon.jpName.contains(
                        name,
                        ignoreCase = true
                    )
                }
                onSuccess()


            }


        }
        if (pokemonList.isEmpty()) {
            errorMessage = "エラー：IDもしくはポケモン名を入力してください"
        } else {
            errorMessage = ""
        }


    }
}
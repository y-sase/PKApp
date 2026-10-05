package com.example.pkapp.viewmodel


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
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
    init {
        Log.d("VM_CREATE", "created")
    }



    var PKListJpName by mutableStateOf("")
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

    private val _state = mutableStateOf(PKListState())//mutableStateOfとvalueはセットで値が随時変わるときに使う
    val state: State<PKListState> = _state
    var isLoading by mutableStateOf(false)
    // var count by mutableStateOf(0)
    var allPokemonList by mutableStateOf<List<PokemonListItem>>(
        emptyList()
    )


    fun loadPokemonList(
        onSuccess: () -> Unit, onError: () -> Unit
    ) {
        if (pokemonList.isNotEmpty()) return

        Log.d(
            "BEFORE_LOAD",
            "size=${pokemonList.size}"
        )
        Log.d("LOAD_LIST", "start")
        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。

            try {


                _state.value = PKListState(isLoading = true)



                when (val result = repository.getPokemonList()) {
                    is NetworkResponse.Loading -> {
                        isLoading = true
                        _state.value = PKListState(isLoading = true)
                    }


                    is NetworkResponse.Success -> {
                        Log.d("LOAD_LIST", "success")
                        Log.d(
                            "LIST_SIZE",
                            "size=${pokemonList.size}"
                        )
                        //pokemonList = result.data?.results ?: emptyList()

                        /*
                    isLoading = false

                    _state.value = PKListState(
                        isLoading = false
                    )
                    */


                        /*
                    pokemonList = pokemonList.map {pokemon ->


                        val responsedetail = repository.getPokemonDetail(pokemon.id)
                        val responsejpname = repository.getPokemonJpName(pokemon.name)
                        Log.d("JP_NAME", ChangeLanguageName(
                            responsedetail,
                            responsejpname
                        ))
                        pokemon.copy(
                            listJpName = ChangeLanguageName(
                                responsedetail,
                                responsejpname)
                        )


                    }


                    val list = result.data?.results ?: emptyList()
                    pokemonList = pokemonList.map { pokemon ->



                    val list = result.data?.results ?: emptyList()
                    pokemonList = list.map { pokemon ->
                    //pokemonList = pokemonList.map { pokemon ->
                       // val responsedetail = repository.getPokemonDetail(pokemon.id)
                        //val responsejpname = repository.getPokemonJpName(pokemon.name)

                        val responsejpname = try {
                            repository.getPokemonJpName(pokemon.name)

                        val speciesName =
                            pokemon.name.substringBefore("-")

                        val responsejpname =try {
                            repository.getPokemonJpName(speciesName)
                        } catch (e: Exception) {


                            Log.e(
                                "JP_ERROR",
                                "name=${pokemon.name}",
                                e
                            )

                            return@map PokemonListItem(
                                name = pokemon.name,
                                url = pokemon.url,
                                listJpName = pokemon.name
                            )
                        }

                        val jpName = ChangeLanguageName(
                            responsejpname
                        )

                        Log.d("JP_NAME", jpName)

                        Log.d(
                            "POKEMON_LIST",
                            "name=${pokemon.name}, jp=$jpName"
                        )
                        PokemonListItem(
                            name = pokemon.name,
                            url = pokemon.url,
                            listJpName = jpName
                        )



                    }*/


                        /*Aパターン

                        val list = result.data?.results ?: emptyList()

                        pokemonList = list.map { pokemon ->

                            val speciesName =
                                if (pokemon.name.startsWith("nidoran-"))
                                    pokemon.name
                                else
                                    pokemon.name.substringBefore("-")

                            val responsejpname = try {
                                repository.getPokemonJpName(speciesName)
                            } catch (e: Exception) {
                                return@map PokemonListItem(
                                    name = pokemon.name,
                                    url = pokemon.url,
                                    listJpName = pokemon.name
                                )
                            }

                            PokemonListItem(
                                name = pokemon.name,
                                url = pokemon.url,
                                listJpName = ChangeLanguageName(responsejpname)
                            )
                        }


 */
                        //Bパターん
                        val list = result.data?.results ?: emptyList()

                        pokemonList = emptyList()

                        for ((index, pokemon) in list.withIndex()) {

                            val speciesName =
                                if (pokemon.name.startsWith("nidoran-"))
                                    pokemon.name
                                else
                                    pokemon.name.substringBefore("-")

                            val responsejpname = try {
                                repository.getPokemonJpName(speciesName)
                            } catch (e: Exception) {

                                pokemonList = pokemonList + PokemonListItem(
                                    name = pokemon.name,
                                    url = pokemon.url,
                                    listJpName = pokemon.name
                                )

                                continue
                            }

                            val jpName = ChangeLanguageName(responsejpname)

                            pokemonList = pokemonList + PokemonListItem(
                                name = pokemon.name,
                                url = pokemon.url,
                                listJpName = jpName
                            )

                            // 最初の1件でロード終了
                            if (index == 0) {
                                _state.value = PKListState(
                                    isLoading = false
                                )
                            }


                        }

                        isLoading = false

                        _state.value = PKListState(
                            isLoading = false
                        )


                        Log.d(
                            "LIST_SIZE",
                            "size=${pokemonList.size}"
                        )

                        allPokemonList = pokemonList

                        Log.d(
                            "ALL_SIZE_SET",
                            "${allPokemonList.size}"
                        )
                        isLoading = false

                        _state.value = PKListState(
                            isLoading = false
                        )

                        onSuccess(

                        )
                    }


                    is NetworkResponse.Failure -> {
                        isLoading = false
                        Log.d("LOADING", "finish")
                        _state.value = PKListState(
                            error = result.error, isLoading = false
                        )
                        onError()

                    }
                }
            }catch (e: Exception) {
                onError()
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
                PKName = ChangeLanguageName(
                    responsejpname
                )
                //PKName = ChangeLanguageName(responsedetail, responsejpname)
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
            Log.d(
                "TYPE_SIZE",
                "pokemonList=${pokemonList.size} all=${allPokemonList.size}"
            )
            onSuccess()
        }
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

        pokemonList = pokemonList.filter { pokemon ->
            //idのポケモン入れる処理
            pokemon.id in favoriteIds
        }
        onSuccess()
    }


    //タイプリセット
    fun resettype() {
        typeIds = emptyList()
        pokemonList = allPokemonList
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
        if (allPokemonList.isEmpty()) {
            Log.d("SEARCH", "allPokemonList empty")
            return
        }

        Log.d(
            "SEARCH_SIZE",
            "before=${pokemonList.size}"
        )
        when {
            id != null -> {
                pokemonList = allPokemonList.filter { pokemon ->
                    //idのポケモン入れる処理
                    pokemon.id == id
                }
                onSuccess()
            }

            name != "" -> {
                Log.d("SEARCH_WORD", name)
                Log.d("ALL_SIZE", "${allPokemonList.size}")
                pokemonList = allPokemonList.filter { pokemon ->
                    //nameのポケモン入れる処理
                    pokemon.listJpName.contains(
                        name, ignoreCase = true
                    )//contains():文字列の中に指定した文字が含まれているか調べる  ignoreCase = true:大文字小文字を無視する
                }
                Log.d(
                    "SEARCH_SIZE",
                    "after=${pokemonList.size}"
                )
                Log.d("SEARCH_RESULT", "${pokemonList.size}")
                onSuccess()


            }


        }
        if (pokemonList.isEmpty()) {
            errorMessage = "エラー：IDもしくはポケモン名を入力してください"
        } else {
            errorMessage = ""
            pokemonList = allPokemonList
        }


    }
}
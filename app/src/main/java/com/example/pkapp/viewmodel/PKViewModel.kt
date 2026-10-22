package com.example.pkapp.viewmodel

import PokemonMemoRepository
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pkapp.DataStoreManager
import com.example.pkapp.SearchBar.TypeListItem
import com.example.pkapp.common.NetworkResponse
//import com.example.pkapp.data.api.StatsListItem
import com.example.pkapp.data.api.repository.PKRepository
import com.example.pkapp.model.ChangeLanguageName
import com.example.pkapp.model.ChangeLanguageType
import com.example.pkapp.model.PokemonListItem
import com.example.pkapp.model.PokemonMemo
import com.example.pkapp.model.Sprites
import com.example.pkapp.pklist.PKListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PKViewModel(
    private val repository: PKRepository,
    private val dataStoreManager: DataStoreManager,
    private val pokemonMemoRepository: PokemonMemoRepository
) : ViewModel() {


    var PKListJpName by mutableStateOf("")
    var PKId by mutableStateOf(0)

    var pokemonId by mutableStateOf(0)

    var PKName by mutableStateOf("")
    var PKHeight by mutableStateOf(0)
    var PKWeight by mutableStateOf(0)
    var PKSprites by mutableStateOf(Sprites(""))
    var PKTypes by mutableStateOf("")
    var query by mutableStateOf("")
    var errorMessage by mutableStateOf("")

    //var favoriteIds by mutableStateOf<List<Int>>(emptyList())
    val favoriteIds = MutableStateFlow<Set<String>>(emptySet())
    init {
        viewModelScope.launch {
            dataStoreManager.getFavorites().collect {
                favoriteIds.value = it
            }
        }
    }
    //private val dataStoreManager = DataStoreManager(context)
    var typeIds by mutableStateOf<List<Int>>(emptyList())
    var typeList by mutableStateOf<List<TypeListItem>>(emptyList())


    var searchId by mutableStateOf(0)
    var pokemonList by mutableStateOf<List<PokemonListItem>>(//<List<PokemonDetailResponse>>はPokemonDetailResponseをたくさん入れられるリスト型
        emptyList()//空っぽのリストを作る関数
    )

    // var formName by mutableStateOf("")

    private val _state = mutableStateOf(PKListState())//mutableStateOfとvalueはセットで値が随時変わるときに使う
    val state: State<PKListState> = _state
    var isLoading by mutableStateOf(false)

    // var count by mutableStateOf(0)
    var allPokemonList by mutableStateOf<List<PokemonListItem>>(
        emptyList()
    )


    var memo by mutableStateOf("")

    //var PKStatsList by mutableStateOf<List<StatsListItem>>(emptyList())
    var PKHp by mutableStateOf(0)
    var PKAttack by mutableStateOf(0)
    var PKDefense by mutableStateOf(0)
    var PKSpecialAttack by mutableStateOf(0)
    var PKSpecialDefense by mutableStateOf(0)
    var PKSpeed by mutableStateOf(0)


    fun loadPokemonList(
        onSuccess: () -> Unit, onError: () -> Unit
    ) {

        if (pokemonList.isNotEmpty()) {

            onSuccess()
            return
        }


        viewModelScope.launch {//コルーチン(時間のかかる処理を、画面を固めずに実行する仕組み)開始。

            try {


                _state.value = PKListState(isLoading = true)



                when (val result = repository.getPokemonList()) {
                    is NetworkResponse.Loading -> {
                        isLoading = true
                        _state.value = PKListState(isLoading = true)
                    }


                    is NetworkResponse.Success -> {


                        val list = result.data?.results ?: emptyList()

                        pokemonList = emptyList()

                        for ((index, pokemon) in list.withIndex()) {

                            var formName =

                                ""


                            val speciesName = pokemon.name
                            val responsejpname = try {
                                repository.getPokemonJpName(speciesName)
                            } catch (e: Exception) {
                                try {
                                    val detail = repository.getPokemonDetail(pokemon.id)

                                    formName = pokemon.name.removePrefix("${detail.species.name}-")

                                    repository.getPokemonJpName(
                                        detail.species.name
                                    )
                                } catch (e: Exception) {


                                    pokemonList = pokemonList + PokemonListItem(
                                        name = pokemon.name,
                                        url = pokemon.url,
                                        listJpName = pokemon.name,
                                        formName = formName
                                    )

                                    continue
                                }
                            }

                            val jpName = ChangeLanguageName(responsejpname)

                            pokemonList = pokemonList + PokemonListItem(
                                name = pokemon.name,
                                url = pokemon.url,
                                listJpName = jpName,
                                formName = formName
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




                        allPokemonList = pokemonList


                        isLoading = false

                        _state.value = PKListState(
                            isLoading = false
                        )
                        errorMessage = ""
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
            } catch (e: Exception) {
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

                val responsejpname = repository.getPokemonJpName(responsedetail.species.name)

                val typeNames = responsedetail.types.map { typeInfo ->//typeInfoは今処理中の1件
                    val typeId =
                        typeInfo.type.url.trimEnd('/').substringAfterLast('/')//最後の / より後ろだけ取得
                            .toInt()//文字列を数値に変換 String->Int

                    val responsejptype = repository.getPokemonJpType(typeId)

                    ChangeLanguageType(
                        responsejptype
                    )
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


                PKHp = responsedetail.stats.find{
                    it.stat.name == "hp"//statsListの中からnameがhpのデータを探して
                }?.base_stat?: 0//見つかったらbase_statを取り出す、もし見つからなかったら０を返す
                PKAttack = responsedetail.stats.find{
                    it.stat.name == "attack"
                }?.base_stat?: 0
                PKDefense = responsedetail.stats.find{
                    it.stat.name == "defense"
                }?.base_stat?: 0
                PKSpecialAttack = responsedetail.stats.find{
                    it.stat.name == "special-attack"
                }?.base_stat?: 0
                PKSpecialDefense = responsedetail.stats.find{
                    it.stat.name == "special-defense"
                }?.base_stat?: 0
                PKSpeed = responsedetail.stats.find{
                    it.stat.name == "speed"
                }?.base_stat?: 0



                //PKStatsList = responsedetail.stats
                val memoData =
                    pokemonMemoRepository.getMemo(PKId)

                memo = memoData?.memo ?: ""

                onSuccess()//取得成功後に画面遷移する

            } catch (e: Exception) {
                onError()


            }
        }
    }
/*
    fun toggleFavorite(id: Int) {

        favoriteIds = if (id in favoriteIds) {
            favoriteIds - id
        } else {
            favoriteIds + id
        }

    }

 */
    fun toggleFavorite(id: Int) {
        viewModelScope.launch {

            val favorites = favoriteIds.value

            if (id.toString() in favorites) {
                dataStoreManager.removeFavorite(id)
            } else {
                dataStoreManager.addFavorite(id)
            }
        }
    }
    fun saveMemo() {
        viewModelScope.launch {

            pokemonMemoRepository.saveMemo(
                PokemonMemo(
                    pokemonId = PKId,
                    memo = memo
                )
            )
        }
    }
    fun loadMemo() {
        viewModelScope.launch {

            val memoData =
                pokemonMemoRepository.getMemo(PKId)

            memo = memoData?.memo ?: ""
        }
    }


    //APIで取得したタイプ一覧リスト
    fun loadPokemonByTypes(
        onSuccess: () -> Unit, onError: () -> Unit
    ) {
        viewModelScope.launch {
            _state.value = PKListState(isLoading = true)

            if (typeIds.isEmpty()) {

                pokemonList = allPokemonList
                isLoading = false
                _state.value = PKListState(
                    isLoading = false
                )
                onSuccess()
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

                        isLoading = false
                        _state.value = PKListState(
                            error = result.error, isLoading = false
                        )
                        onError()

                    }


                }
            }
            //pokemonList = filteredList ?: emptyList()
            val typePokemonNames = filteredList?.map { it.name }?.toSet() ?: emptySet()

            pokemonList = allPokemonList.filter {
                it.name in typePokemonNames
            }

            isLoading = false
            _state.value = PKListState(
                isLoading = false
            )
            pokemonList.take(10).forEach {

            }
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
            //pokemon.id in favoriteIds
            pokemon.id.toString() in favoriteIds.value
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

            return
        }


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
                    //nameのポケモン入れる処理
                    pokemon.listJpName.contains(
                        name, ignoreCase = true
                    )//contains():文字列の中に指定した文字が含まれているか調べる  ignoreCase = true:大文字小文字を無視する
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